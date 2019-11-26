package br.ufrpe.wanderlustapp.hopitais.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.ufrpe.wanderlustapp.R;
import br.ufrpe.wanderlustapp.hopitais.dominio.Hospitais;
import br.ufrpe.wanderlustapp.hopitais.gui.adapter.ListHospitalAdapter;
import br.ufrpe.wanderlustapp.hopitais.negocio.HospitaisServices;
import br.ufrpe.wanderlustapp.infra.Sessao;

public class ListaHospitaisActivity extends AppCompatActivity {

    HospitaisServices hospitaisServices = new HospitaisServices(this);
    public static final String TITULO_APPBAR_LISTA = "Lista de Hospitais";
    private ListHospitalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_hospitais);
        setTitle(TITULO_APPBAR_LISTA);
        configuraRecyclerview();
        configuraBtnInserePonto();
    }



    private void configuraBtnInserePonto() {
        TextView btnInserePonto = findViewById(R.id.lista_insere_hospital);
        btnInserePonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaHospitaisActivity.this, CadastraHospitaisActivity.class));
            }
        });
    }

    private void insereHospital(Hospitais hospital) {
        try {
            hospitaisServices.cadastrar(hospital);
            adapter.adiciona(hospital);
            Toast.makeText(getApplicationContext(), "Hospital: " + hospital.getNome() + " cadastrado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Hospital: " + hospital.getNome() + " Já cadastrado", Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        Hospitais hospital = Sessao.instance.getHospital();
        if (hospital != null){
            insereHospital(hospital);
            Sessao.instance.resetHospital();
        }

        super.onResume();
    }

    private List<Hospitais> geraLista(){
        return hospitaisServices.getLista();
    }

    private void setAdapter (RecyclerView recyclerView){
        adapter = new ListHospitalAdapter(this,geraLista());
        recyclerView.setAdapter(adapter);

    }


    private void configuraRecyclerview() {
        RecyclerView listaHospitais = findViewById(R.id.lista_hospital_recyclerview);
        setAdapter(listaHospitais);
    }

}
