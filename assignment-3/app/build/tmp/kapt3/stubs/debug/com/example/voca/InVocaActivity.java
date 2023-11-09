package com.example.voca;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0015J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006\u0019"}, d2 = {"Lcom/example/voca/InVocaActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "_inVocaList", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/voca/MyDataTypes$VocaListSpecificInfo;", "_inVocaList_Voca", "", "Lcom/example/voca/MyDataTypes$Voca;", "binding", "Lcom/example/voca/databinding/ActivityInVocaBinding;", "binding2", "Lcom/example/voca/databinding/SpecificVocaListBinding;", "inVocaList", "Landroidx/lifecycle/LiveData;", "getInVocaList", "()Landroidx/lifecycle/LiveData;", "inVocaList_Voca", "getInVocaList_Voca", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "openDialog", "data", "app_debug"})
public final class InVocaActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.voca.databinding.ActivityInVocaBinding binding;
    private com.example.voca.databinding.SpecificVocaListBinding binding2;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.voca.MyDataTypes.VocaListSpecificInfo> _inVocaList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.voca.MyDataTypes.VocaListSpecificInfo> inVocaList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.voca.MyDataTypes.Voca>> _inVocaList_Voca = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.voca.MyDataTypes.Voca>> inVocaList_Voca = null;
    
    public InVocaActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.voca.MyDataTypes.VocaListSpecificInfo> getInVocaList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.voca.MyDataTypes.Voca>> getInVocaList_Voca() {
        return null;
    }
    
    @java.lang.Override
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.TIRAMISU)
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public final void openDialog(@org.jetbrains.annotations.NotNull
    com.example.voca.MyDataTypes.Voca data) {
    }
}