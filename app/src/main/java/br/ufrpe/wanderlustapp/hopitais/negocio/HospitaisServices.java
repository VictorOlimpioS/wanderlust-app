package br.ufrpe.wanderlustapp.hopitais.negocio;

import android.content.Context;

import java.util.List;

import br.ufrpe.wanderlustapp.hopitais.dominio.Hospitais;
import br.ufrpe.wanderlustapp.hopitais.persistencia.HospitaisDAO;
import br.ufrpe.wanderlustapp.pontoTuristico.dominio.PontoTuristico;
import br.ufrpe.wanderlustapp.pontoTuristico.persistencia.PontoTuristicoDAO;

public class HospitaisServices {
    private HospitaisDAO hospitaisDAO;

    public HospitaisServices(Context context) { hospitaisDAO = new HospitaisDAO(context); }

    public void cadastrar(Hospitais hospital) throws Exception {
        if (hospitaisDAO.getPHospitalByNome(hospital.getNome()) != null){
            throw new Exception();
        }
        long idHospital = hospitaisDAO.cadastrar(hospital);
        hospital.setId(idHospital);
    }

    public List<Hospitais> getLista(){
        return hospitaisDAO.getListHospitais();
    }

}
