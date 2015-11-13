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
 * Author:hezhiping   Email:110476592@qq.com
 */
package cn.gorun8.easyfk.party.utils;

import cn.gorun8.easyfk.base.util.Debug;
import cn.gorun8.easyfk.base.util.UtilMisc;
import cn.gorun8.easyfk.base.util.UtilValidate;
import cn.gorun8.easyfk.entity.GenericEntityException;
import cn.gorun8.easyfk.entity.GenericValue;
import cn.gorun8.easyfk.party.dao.PartyDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



/**
 * Makes it easier to deal with entities that follow the
 * extensibility pattern and that can be of various types as identified in the database.
 */
public class PartyTypeUtil {

    public static final String module = PartyTypeUtil.class.getName();

    public static boolean isType(PartyDao partyDao, Collection<GenericValue> thisCollection, String typeRelation, GenericValue targetType) {
        for (GenericValue value: thisCollection) {
            try {
                GenericValue related =  partyDao.findPartyTypeById(typeRelation);
                 //value.getRelatedOne(typeRelation);
                if (isType(partyDao,related, targetType)) {
                    return true;
                } // else keep looking
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }



    private static GenericValue getParentType(PartyDao partyDao, GenericValue typeValue) {
        // assumes Parent relation is "Parent<entityName>"
        try {
            String partyTypeId = typeValue.getString("parentTypeId");
            if(UtilValidate.isNotEmpty(partyTypeId)) {
                return partyDao.findPartyTypeById(partyTypeId);
                //typeValue.getRelatedOneCache("Parent" + typeValue.getEntityName());
            }
            return null;
        } catch (Exception e) {
            Debug.logWarning(e, module);
            return null;
        }
    }

    public static List<GenericValue> getDescendantTypes(PartyDao partyDao,GenericValue typeValue) {
        // assumes Child relation is "Child<entityName>"
        List<GenericValue> descendantTypes = new ArrayList<GenericValue>();

        // first get all childrenTypes ...
        List<GenericValue> childrenTypes = null;
        try {
            String id = typeValue.getString("partyTypeId");
            childrenTypes =partyDao.findDescendantTypes(id);
            //childrenTypes = typeValue.getRelatedCache("Child" + typeValue.getEntityName());
        } catch (Exception e) {
            Debug.logWarning(e, module);
            return null;
        }
        if (childrenTypes == null)
            return null;

        // ... and add them as direct descendants
        descendantTypes.addAll(childrenTypes);

        // then add all descendants of the children
        for (GenericValue childType: childrenTypes) {
            List<GenericValue> childTypeDescendants = getDescendantTypes(partyDao,childType);
            if (childTypeDescendants != null) {
                descendantTypes.addAll(childTypeDescendants);
            }
        }

        return descendantTypes;
    }

    public static boolean isType(PartyDao partyDao,GenericValue thisType, GenericValue targetType) {
        if (thisType == null) {
            return false;
        } else if (targetType.equals(thisType)) {
            return true;
        } else {
            return isType(partyDao,getParentType(partyDao, thisType), targetType);
        }
    }

    /**
     * A generic method to be used on Type enities, e.g. ProductType.  Recurse to the root level in the type hierarchy
     * and checks if the specified type childType has parentType as its parent somewhere in the hierarchy.
     *
     * @param entityName      Name of the Type entity on which check is performed.
     * @param primaryKey      Primary Key field of the Type entity.
     * @param childType       Type value for which the check is performed.
     * @param parentTypeField Field in Type entity which stores the parent type.
     * @param parentType      Value of the parent type against which check is performed.
     * @return boolean value based on the check results.
     */
    public static boolean hasParentType(PartyDao partyDao,String entityName, String primaryKey, String childType, String parentTypeField, String parentType) {
        GenericValue childTypeValue = null;
        try {
            childTypeValue = partyDao.findPartyTypeById(childType);
            //delegator.findOne(entityName, UtilMisc.toMap(primaryKey, childType), true);
        } catch (Exception e) {
            Debug.logError("Error finding "+entityName+" record for type "+childType, module);
        }
        if (childTypeValue != null) {
            if (parentType.equals(childTypeValue.getString(primaryKey))) return true;

            if (childTypeValue.getString(parentTypeField) != null) {
                if (parentType.equals(childTypeValue.getString(parentTypeField))) {
                    return true;
                } else {
                    return hasParentType(partyDao, entityName, primaryKey, childTypeValue.getString(parentTypeField), parentTypeField, parentType);
                }
            }
        }

        return false;
    }
}
