package projetofaculdade.example.ifood_ucsal.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracaoFirebase {
    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth referenciaAtutenticacao;
    private static StorageReference referenciaStorage;
    public static  String getIdUsuario(){
        FirebaseAuth autenticacao = getFirebaseAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

public static  DatabaseReference getFirebase(){
    if (referenciaFirebase == null){
        referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
    return referenciaFirebase;
    }
    public static FirebaseAuth getFirebaseAutenticacao(){
    if (referenciaAtutenticacao == null){
        referenciaAtutenticacao= FirebaseAuth.getInstance();
    }
    return referenciaAtutenticacao;
    }
    public static StorageReference getFirebaseStorage(){
        if (referenciaStorage == null){
            referenciaStorage= FirebaseStorage.getInstance().getReference();
        }
        return referenciaStorage;
    }
}

