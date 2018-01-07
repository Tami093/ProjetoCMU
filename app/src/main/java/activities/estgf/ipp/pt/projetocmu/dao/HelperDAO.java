package activities.estgf.ipp.pt.projetocmu.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HelperDAO extends SQLiteOpenHelper {

    private String sqlCriacaoTabelas;

    public HelperDAO(Context context) {
        super(context, "ProjetoCMUBancoDados", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        sqlCriacaoTabelas = "CREATE TABLE IF NOT EXISTS VAGAS (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nomevaga TEXT NOT NULL," +
                "nomeempresa TEXT," +
                "tipovaga TEXT," +
                "salario TEXT," +
                "localtrabalho TEXT," +
                "vagaativa TEXT);";
        db.execSQL(sqlCriacaoTabelas);


        sqlCriacaoTabelas = "CREATE TABLE Empresas " +
                "(id INTEGER PRIMARY KEY," +
                "nome TEXT NOT NULL," +
                "email TEXT," +
                "endereco TEXT," +
                "telefone TEXT);";

        db.execSQL(sqlCriacaoTabelas);


        //insereVagasAutomaticoAoCriarBanco();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {

    }


}
