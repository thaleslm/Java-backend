package app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import app.controller.InformationSource;
import app.controller.MessageReturn;
import app.controller.Search;
import app.data.ConnectionMongo;

public class SearchDataDynamic {
    private static SearchDataDynamic instance;
    private ConnectionMongo  mongo = ConnectionMongo.getInstance();

       public static SearchDataDynamic getInstance() {
        if (instance == null) {
            instance = new SearchDataDynamic();
        }
        return instance;
    }


    public MessageReturn queryStringMongo(InformationSource informationSource){
        // List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
        try {
            List<Map<String, Object>> cables = mongo.searchCables(informationSource);

            for(Search information : informationSource.searchList){
                System.out.println("result list"+searchCablesByTemperature(cables,information.attributeName, information.attributeValue,information.operator));
            }
            
            // List<Map<String,Object>> resultSearch = searchCablesByTemperature(cables,information.attributeName, information.attributeValue,information.operator);
            return new MessageReturn("Sucess", null);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new MessageReturn("fail", null);

        }
    }

    public  List<Map<String, Object>> searchCablesByTemperature(List<Map<String, Object>> cables, String attributeName, Object attributeValue, String operator) {




        
        if(attributeName.equals("all")){ //"attributeName":"all" para retornar todos
            System.out.println("cables: "+ attributeName);
            return cables;
        }else {
            return cables.stream()
                    .filter(cable -> {
                        Object cableValue = cable.get(attributeName);
    
                        if (cableValue instanceof Integer && attributeValue instanceof Integer) {
                            int intValue = (int) attributeValue;
                            switch (operator) {
                                case "==":
                                    return (int) cableValue == intValue;
                                case "!=":
                                    return (int) cableValue != intValue;
                                case ">":
                                    return (int) cableValue > intValue;
                                case "<":
                                    return (int) cableValue < intValue;
                                case "<=":
                                    return (int) cableValue <= intValue;
                                case ">=":
                                    return (int) cableValue >= intValue;
                                default:
                                    throw new IllegalArgumentException("Operador não suportado: " + operator);
                            }
                        } else if (cableValue instanceof String && cableValue instanceof String) {
                            String stringValue = (String) attributeValue;
                            
                            return cableValue.equals(stringValue);

                        } else {
                            throw new IllegalArgumentException("Tipos de dados incompatíveis para comparação");
                        }
                    })
                    .collect(Collectors.toList());
        }
            

            
        }




        
    }

// {
// "typeDispositivo": "cadastro_ativos",
// "cadastral":true,
// "which":"todos",
// "attributeName":"all",
// "attributeValue":10,
// "comparison":""
// }



//   switch (operator) {
//                 case "==":
//                 return cables.stream()
//                 .filter(cable -> (int) cable.get(attributeName) == attributeValue)
//                 .collect(Collectors.toList());
//                 case "!=":
//                     return cables.stream()
//                     .filter(cable -> (int) cable.get(attributeName) != attributeValue)
//                     .collect(Collectors.toList());
//                 case ">":
//                     return cables.stream()
//                     .filter(cable -> (int) cable.get(attributeName) > attributeValue)
//                     .collect(Collectors.toList());
//                 case "<":
//                     return cables.stream()
//                     .filter(cable -> (int) cable.get(attributeName) < attributeValue)
//                     .collect(Collectors.toList());
//                 case "<=":
//                     return cables.stream()
//                     .filter(cable -> (int) cable.get(attributeName) <= attributeValue)
//                     .collect(Collectors.toList());
//                 case ">=":
//                     return cables.stream()
//                     .filter(cable -> (int) cable.get(attributeName) >= attributeValue)
//                     .collect(Collectors.toList());
//                     default:
//                     throw new IllegalArgumentException("Operador não suportado: " + operator);
//             }
