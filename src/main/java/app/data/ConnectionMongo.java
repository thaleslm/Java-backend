package app.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import app.controller.Cable;
import app.controller.InformationSource;
import app.controller.MessageReturn;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class ConnectionMongo {
    private static ConnectionMongo instance;
    private MongoDatabase database;
    private MongoClient mongoClient;
    private String uriDatabase; //
    /*
     * Singleton da Classe
     */
    public static ConnectionMongo getInstance() {
        if (instance == null) {
            instance = new ConnectionMongo();
        }
        return instance;
    }

    public void startConnectionWithMongo() {
        try {
            if (uriDatabase == null) {
                uriDatabase = "mongodb://dpiot:xxxxxxxxR@127.0.0.1:27017/?authSource=admin";
            }
            MongoClientURI mongoClientUri = new MongoClientURI(uriDatabase);
            this.mongoClient = new MongoClient(mongoClientUri);
            this.database = mongoClient.getDatabase("test");
            System.out.println("-------------------------------------------------        CONECTADO         ------------------------------------------------- ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: "+e);
        }
    }

    public void closeConnectionWithMongo() {
        try {
            this.mongoClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public  List<Map<String, Object>> searchCables(InformationSource informationSource ){
        List<Map<String, Object>> cableList = new ArrayList<>();
        try{
            MongoCollection<Document> collection = this.database.getCollection(informationSource.typeDispositivo);
            FindIterable<Document> documents = collection.find();
            Gson gson = new Gson();
            
            for (Document document : documents) {
                Cable cable = gson.fromJson(document.toJson(),Cable.class);
                cableList.add(cable.getAllData());
            }
            return  cableList;
        }
        catch(Exception e){
            System.out.println("Error: "+e);
            return null;
        }
    }



}


