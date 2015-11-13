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
 * Author:hezhiping   Email:110476592@qq.com   Date: 15-11-4
 */
package cn.gorun8.easyfk.party.service;

import cn.gorun8.easyfk.entity.GenericValue;

import java.util.List;
import java.util.Map;

public interface PartyService {

    /**
     * list party
     * @param context
     * @return
     */
    public List<Map> listParty(Map<String, ? extends Object> context);

    /**
     * get a party group
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String,Object> findPartyGorup(Map<String,Object> context);


    /**
     * Deletes a Party.
     *
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> deleteParty(Map<String, ? extends Object> context);

    /**
     * Creates a Person.
     * If no partyId is specified a numeric partyId is retrieved from the Party sequence.
     *
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> createPerson(Map<String, ? extends Object> context);

    /**
     * Sets a party status.
     * <b>security check</b>: the status change must be defined in StatusValidChange.
     */
    public Map<String, Object> setPartyStatus(Map<String, ? extends Object> context);

    /**
     * Updates a Person.
     *
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> updatePerson(Map<String, ? extends Object> context);


    /**
     * Creates a PartyGroup.
     * If no partyId is specified a numeric partyId is retrieved from the Party sequence.
     *
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> createPartyGroup(Map<String, ? extends Object> context);

    /**
     * Updates a PartyGroup.
     *
     * @param context Map containing the input parameters.
     * @return Map with the result of the service, the output parameters.
     */
    public Map<String, Object> updatePartyGroup(Map<String, ? extends Object> context);


}
