package com.example.voca;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0005J\u001b\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\'\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\f2\b\b\u0001\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/voca/MyRestAPI;", "", "getVocaLists", "", "Lcom/example/voca/MyDataTypes$VocaListInfo;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getVocaSpecificInfo", "Lcom/example/voca/MyDataTypes$VocaListSpecificInfo;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendNewVocaList", "Lretrofit2/Response;", "data", "Lcom/example/voca/MyDataTypes$NewVocaList;", "(Lcom/example/voca/MyDataTypes$NewVocaList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface MyRestAPI {
    
    @retrofit2.http.GET(value = "/myapp/v1/word_lists")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getVocaLists(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.voca.MyDataTypes.VocaListInfo>> $completion);
    
    @retrofit2.http.POST(value = "/myapp/v1/word_list")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object sendNewVocaList(@retrofit2.http.Body
    @org.jetbrains.annotations.NotNull
    com.example.voca.MyDataTypes.NewVocaList data, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.example.voca.MyDataTypes.VocaListInfo>>> $completion);
    
    @retrofit2.http.GET(value = "/myapp/v1/word_list/{id}")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getVocaSpecificInfo(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.voca.MyDataTypes.VocaListSpecificInfo> $completion);
}