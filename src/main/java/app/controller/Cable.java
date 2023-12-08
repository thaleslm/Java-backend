package app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

public class Cable {
    private Id id;
    private Coordenada pac1;
    private Coordenada pac2;
    private List<Coordenada> listaCoordenadasTrecho;
    private int faseDoCabo;
    private int temperaturaNominalCabo;
    private String fabricanteCabo;
    private String modeloCabo;
    private int tipoIsolanteCabo;
    private int secaoNominalCabo;
    private int classeTensaoCabo;
    private List<Integer> codIdIedAssociadoAoCabo;
    private String identificacaoCircuito;
    private String dataCadastro;
    private int jusante;
    private int montante;
    private int codId;
    private int escopo;
    private String dnsIot;
    private boolean ativada;
    private List<Integer> gruposAtivosTwins;
    private String dataFabricacao;
    private int codIdSE;

    public static class Id {
        private String $oid;
    }

    public Map<String, Object> getAllData() {
        Map<String, Object> allData = new HashMap<>();
        allData.put("id", id);
        allData.put("pac1", pac1);
        allData.put("pac2", pac2);
        allData.put("listaCoordenadasTrecho", listaCoordenadasTrecho);
        allData.put("faseDoCabo", faseDoCabo);
        allData.put("temperaturaNominalCabo", temperaturaNominalCabo);
        allData.put("fabricanteCabo", fabricanteCabo);
        allData.put("modeloCabo", modeloCabo);
        allData.put("tipoIsolanteCabo", tipoIsolanteCabo);
        allData.put("secaoNominalCabo", secaoNominalCabo);
        allData.put("classeTensaoCabo", classeTensaoCabo);
        allData.put("codIdIedAssociadoAoCabo", codIdIedAssociadoAoCabo);
        allData.put("identificacaoCircuito", identificacaoCircuito);
        allData.put("dataCadastro", dataCadastro);
        allData.put("jusante", jusante);
        allData.put("montante", montante);
        allData.put("codId", codId);
        allData.put("escopo", escopo);
        allData.put("dnsIot", dnsIot);
        allData.put("ativada", ativada);
        allData.put("gruposAtivosTwins", gruposAtivosTwins);
        allData.put("dataFabricacao", dataFabricacao);
        allData.put("codIdSE", codIdSE);

        return allData;
    }


  

    public Integer getTemperaturaNominalCabo(){
        return temperaturaNominalCabo;
    }

}
 class Coordenada {
        public double lat;
        public double lng;        
    }