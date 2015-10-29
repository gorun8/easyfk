package cn.gorun8.easyfk.base.util;

/**
 * 通用的枚举值
 * 
 */
public class Generic {

	/**
	 * 表示系统中的是否
	 * 
	 */
	public enum YN {
		Y, N;
	}

	/**
	 * 表示系统中执行请求后返回信息的状态类型
	 * 
	 * 
	 */
	public enum MsgType {
		/**
		 * 警告信息
		 */
		WARN("warn"),
		/**
		 * 成功信息
		 */
		SUCCESS("success"),
		/**
		 * 错误信息
		 */
		ERROR("error");

		private String key = "";

		private MsgType(String key) {
			this.key = key;
		}

		@Override
		public String toString() {
			return this.key;
		}
	}
	
	public enum ParamName{
		/**
		 * 访问url
		 */
		
		VISITURL("visiturl"),
		
		/**
		 * 应用sessionid
		 */
		JSESSIONID("jsessionid"),
		
		/**
		 * 票据
		 */
		TICKET("ticket"),
		
		/**
		 * 应用sessionMap对象
		 */
		APPSESSIONMAP("appsessionmap");
		
		private String key = "";
		
		private ParamName(String key){
			this.key = key;
		}
		@Override
		public String toString() {
			return this.key;
		}
		
	}

}
