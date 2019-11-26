package br.ufrpe.wanderlustapp.hopitais.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.ufrpe.wanderlustapp.cidade.persistencia.CidadeDAO;
import br.ufrpe.wanderlustapp.hopitais.dominio.Hospitais;
import br.ufrpe.wanderlustapp.infra.persistencia.AbstractDAO;
import br.ufrpe.wanderlustapp.infra.persistencia.DBHelper;
import br.ufrpe.wanderlustapp.pontoTuristico.dominio.PontoTuristico;


public class HospitaisDAO extends AbstractDAO {
    private SQLiteDatabase db;
    private DBHelper helper;
    private Context context;

    public HospitaisDAO(Context context) {
        this.context = context;
        helper = new DBHelper(context);
    }

    private ContentValues getContentValues(Hospitais hospital) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.CAMPO_FK_CIDADE_HOSPITAL, hospital.getCidade().getId());
        values.put(DBHelper.CAMPO_NOME_HOSPITAL, hospital.getNome());
        values.put(DBHelper.CAMPO_DESCRICAO_HOSPITAL, hospital.getDescricao());
        return values;
    }

    private Cursor getCursor(List<Hospitais> hospitais, String sql) {
        Cursor cursor = db.rawQuery(sql, new String[]{});
        while (cursor.moveToNext()){
            hospitais.add(createHospitais(cursor));
        }
        return cursor;
    }

    private void setsHospitais(Cursor cursor, Hospitais hospitais, CidadeDAO cidadeDAO) {
        int columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_ID_HOSPITAIL);
        hospitais.setId(Integer.parseInt(cursor.getString(columnIndex)));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_NOME_HOSPITAL);
        hospitais.setNome(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_DESCRICAO_HOSPITAL);
        hospitais.setDescricao(cursor.getString(columnIndex));
        columnIndex = cursor.getColumnIndex(DBHelper.CAMPO_FK_CIDADE_HOSPITAL);
        hospitais.setCidade(cidadeDAO.getCidade(cursor.getInt(columnIndex)));
    }

    public Hospitais getPontoTuristicoById(long id) {
        Hospitais hospital = null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_HOSPITAIS + " WHERE " + DBHelper.CAMPO_ID_HOSPITAIL + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{Long.toString(id)});
        hospital = getHospital(hospital, cursor);
        super.close(cursor, db);
        return hospital;
    }

    private Hospitais getHospital(Hospitais hospital, Cursor cursor) {
        if (cursor.moveToFirst()) {
            hospital = createHospitais(cursor);
        }
        return hospital;
    }

    public Hospitais getPHospitalByNome(String nome) {
        Hospitais hospital= null;
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_HOSPITAIS + " WHERE " + DBHelper.CAMPO_NOME_HOSPITAL + " LIKE ?;";
        Cursor cursor = db.rawQuery(sql, new String[]{nome});
        hospital = getHospital(hospital, cursor);
        super.close(cursor, db);
        return hospital;
    }

    public List<Hospitais> getListHospitais(){
        List<Hospitais> hospitais = new ArrayList<Hospitais>();
        db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + DBHelper.TABELA_HOSPITAIS;
        Cursor cursor = getCursor(hospitais, sql);
        cursor.close();
        db.close();
        return hospitais;
    }

    private Hospitais createHospitais(Cursor cursor) {
        Hospitais hospital = new Hospitais();
        CidadeDAO cidadeDAO = new CidadeDAO(context);
        setsHospitais(cursor, hospital, cidadeDAO);
        return hospital;
    }

    public long cadastrar(Hospitais hospital){
        db = helper.getWritableDatabase();
        ContentValues values = getContentValues(hospital);
        long id = db.insert(DBHelper.TABELA_HOSPITAIS,null,values);
        super.close(db);
        return id;
    }

}

