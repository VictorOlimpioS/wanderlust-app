package br.ufrpe.wanderlustapp.hopitais.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.ufrpe.wanderlustapp.R;
import br.ufrpe.wanderlustapp.cidade.dominio.Cidade;
import br.ufrpe.wanderlustapp.cidade.negocio.CidadeServices;
import br.ufrpe.wanderlustapp.hopitais.dominio.Hospitais;
import br.ufrpe.wanderlustapp.infra.Sessao;
import br.ufrpe.wanderlustapp.pais.dominio.Pais;
import br.ufrpe.wanderlustapp.pais.negocio.PaisServices;


public class CadastraHospitaisActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_INSERE = "Hospitais";
    CidadeServices cidadeServices = new CidadeServices(this);
    PaisServices paisServices = new PaisServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_hospitais);
        setTitle(TITULO_APPBAR_INSERE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fomrulario_salva_hospital, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_formulario_hospital_ic_salva) {
            Hospitais hospital = criaHospital();
            if (verficaCampos()) {
                Sessao.instance.setHospital(hospital);
            }
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Hospitais criaHospital() {
        Hospitais hospital = new Hospitais();
        if (verficaCampos()) {
            preencheAtributosPonto(hospital);
        }
        return hospital;
    }

    private boolean verficaCampos() {
        EditText nome = findViewById(R.id.formulario_hospital_nome);
        EditText descricao = findViewById(R.id.formulario_hospital_descricao);
        return nome.length() > 0 && descricao.length() > 0;
    }

    private void preencheAtributosPonto(Hospitais hospital) {
        EditText nome = findViewById(R.id.formulario_hospital_nome);
        EditText descricao = findViewById(R.id.formulario_hospital_descricao);
        hospital.setNome(nome.getText().toString());
        hospital.setDescricao(descricao.getText().toString());
        hospital.setCidade(createCidadePadrao());
    }

    private Cidade createCidadePadrao() {
        Pais pais = new Pais();
        pais.setNome("Brasil");
        paisServices.cadastrar(pais);
        Cidade cidade = new Cidade();
        cidade.setNome("Recife");
        cidade.setPais(pais);
        cidadeServices.cadastrar(cidade);
        return cidade;
    }

}


















