package com.example.voca;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019J\u0016\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u00162\b\b\u0001\u0010 \u001a\u00020!R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/example/voca/MyViewModel;", "Landroidx/lifecycle/ViewModel;", "api", "Lcom/example/voca/MyRestAPI;", "(Lcom/example/voca/MyRestAPI;)V", "_inVocaList", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/voca/MyDataTypes$VocaListSpecificInfo;", "_inVocaList_Voca", "", "Lcom/example/voca/MyDataTypes$Voca;", "_vocaList", "Lcom/example/voca/MyDataTypes$VocaListInfo;", "inVocaList", "Landroidx/lifecycle/LiveData;", "getInVocaList", "()Landroidx/lifecycle/LiveData;", "inVocaList_Voca", "getInVocaList_Voca", "vocaList", "getVocaList", "getVocaListFromServer", "", "getVocaListSpecificInfoFromServer", "id", "", "openDialog", "context", "Landroid/content/Context;", "binding", "Lcom/example/voca/databinding/NewVocaListBinding;", "postVocaListToServer", "data", "Lcom/example/voca/MyDataTypes$NewVocaList;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel
public final class MyViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.voca.MyRestAPI api = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.voca.MyDataTypes.VocaListInfo>> _vocaList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.voca.MyDataTypes.VocaListInfo>> vocaList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.voca.MyDataTypes.VocaListSpecificInfo> _inVocaList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.voca.MyDataTypes.VocaListSpecificInfo> inVocaList = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.voca.MyDataTypes.Voca>> _inVocaList_Voca = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.voca.MyDataTypes.Voca>> inVocaList_Voca = null;
    
    @javax.inject.Inject
    public MyViewModel(@org.jetbrains.annotations.NotNull
    com.example.voca.MyRestAPI api) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.voca.MyDataTypes.VocaListInfo>> getVocaList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.voca.MyDataTypes.VocaListSpecificInfo> getInVocaList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.voca.MyDataTypes.Voca>> getInVocaList_Voca() {
        return null;
    }
    
    public final void getVocaListFromServer() {
    }
    
    public final void postVocaListToServer(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.example.voca.MyDataTypes.NewVocaList data) {
    }
    
    public final void getVocaListSpecificInfoFromServer(int id) {
    }
    
    public final void openDialog(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.example.voca.databinding.NewVocaListBinding binding) {
    }
}