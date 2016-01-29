/*
 * Project:Easy Web Framework
 * Description:
 * EasyFK stands for Easy Web Framework.It's an open source product for E-Business / E-Commerce.It
 * was launched by a chinese Hezhiping(QQ:110476592) in 2015.The goal of EasyFK is to  provide a
 * foundation and starting point for reliable, secure , simple-to-use ,cost-effective ,scalable
 * and suitable-for-Chinese E-Business / E-Commerce solutions. With EasyFK, you can get started
 * right away without the huge deployment and maintenance costs of E-Business / E-Commerce systems.
 * Of course, you can customize it or use it as a framework to implement your most challenging business needs.
 * EasyFk is licensed under the Apache License Version 2.0.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-12-23
 */
package cn.gorun8.easyfk.base.util;

import javolution.util.FastList;
import javolution.util.FastMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 用于缓存异步操作时的消息。
 */
public class UtilMessageCache {
    private final static int DEFAULT_MAX = 1000;
    private final static String EASYFK_NO_MORE_DATA = "EASYFK_NO_MORE_DATA";

    private static Map<String ,Queue<MessageItem>> MESSAGE_CACHE= FastMap.newInstance();


    public synchronized static void addErrorMessage(String categoryId, String message ,String percent,boolean finished ) {
        addMessage(UtilMessages.RESPOND_ERROR,categoryId,  message ,percent, finished );
    }

    public synchronized static void addSuccessMessage(String categoryId, String message,String percent ,boolean finished ) {
        addMessage(UtilMessages.RESPOND_SUCCESS,categoryId,  message,percent , finished );
    }

    public  static  void addMessage(String type,String categoryId, String message  ,String percent) {
        addMessage(type,categoryId,message,percent,DEFAULT_MAX,false);
    }

    public  static  void addMessage(String type,String categoryId, String message,String percent,boolean finished ) {
        addMessage(type,categoryId,message,percent,DEFAULT_MAX,finished);
    }

    public synchronized static void addMessage(String type,String categoryId, String message,String percent ,int maxSize,boolean finished ){
        Queue<MessageItem>  messageQueue =  MESSAGE_CACHE.get(categoryId);
        if(messageQueue == null){
            messageQueue = new LinkedList<MessageItem>();
            MESSAGE_CACHE.put(categoryId,messageQueue);
        }

        while(messageQueue.size() >= maxSize){
            messageQueue.poll();
        }

        messageQueue.offer(new MessageItem(type,message,percent));

        if(finished){
            messageQueue.offer(new MessageItem(type,EASYFK_NO_MORE_DATA));
        }
    }

    public static List<MessageItem> getMessage(String categoryId){
        Queue<MessageItem>  messageQueue =  MESSAGE_CACHE.get(categoryId);
        List<MessageItem> msg = FastList.newInstance();
        boolean finished = false;
        if(messageQueue != null) {
            int count = messageQueue.size();

            for (int i = 0 ; i < count ; ++ i){
                MessageItem t = messageQueue.poll();
                if(EASYFK_NO_MORE_DATA.equals(t.getMessage()))
                {
                    finished = true;
                }
                msg.add(t);
            }
        }
        if(finished){
            clearMessageCache(categoryId);
        }
        return msg;
    }

    public static synchronized void clearMessageCache(String categoryId){
        Queue<MessageItem>  messageQueue =  MESSAGE_CACHE.get(categoryId);
        if(messageQueue != null) {
            messageQueue.clear();
            MESSAGE_CACHE.remove(categoryId);
        }
    }

    public static class MessageItem{
        String type;
        String message;
        String percent;

        public MessageItem(String type, String message,String percent) {
            this.type = type;
            this.message = message;
            this.percent = percent;
        }

        public MessageItem(String type, String message) {
            this.type = type;
            this.message = message;
            this.percent = "";
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }
    }

}
