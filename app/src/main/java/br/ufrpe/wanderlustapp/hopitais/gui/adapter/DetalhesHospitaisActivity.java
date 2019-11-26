package br.ufrpe.wanderlustapp.hopitais.gui.adapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

import br.ufrpe.wanderlustapp.R;
import br.ufrpe.wanderlustapp.cidade.dominio.Cidade;
import br.ufrpe.wanderlustapp.cidade.negocio.CidadeServices;
import br.ufrpe.wanderlustapp.hopitais.dominio.Hospitais;
import br.ufrpe.wanderlustapp.infra.Sessao;
import br.ufrpe.wanderlustapp.pais.dominio.Pais;
import br.ufrpe.wanderlustapp.pais.negocio.PaisServices;
import br.ufrpe.wanderlustapp.pontoImagem.dominio.PontoImagem;
import br.ufrpe.wanderlustapp.pontoImagem.negocio.PontoImagemServices;
import br.ufrpe.wanderlustapp.pontoTuristico.dominio.PontoTuristico;
import br.ufrpe.wanderlustapp.pontoTuristico.negocio.PontoTuristicoServices;

import static br.ufrpe.wanderlustapp.pontoTuristico.gui.pontosActivityConstantes.CHAVE_PONTO;
import static br.ufrpe.wanderlustapp.pontoTuristico.gui.pontosActivityConstantes.POSICAO_INVALIDA_PONTO;

public class DetalhesHospitaisActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR_HOSPITAL = "Lista de Hospitais";
    private TextView nomeHospital;
    private TextView descricaoHospital;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_hospitais);
        setTitle(TITULO_APPBAR_HOSPITAL);

        Hospitais hospital = Sessao.instance.getHospital();
        nomeHospital = findViewById(R.id.detalhe_hospital_nome);
        nomeHospital.setText(hospital.getNome());
        descricaoHospital= findViewById(R.id.detalhe_hospital_descricao);
        descricaoHospital.setText(hospital.getDescricao());

    }
}
