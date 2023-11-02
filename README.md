本项目主要是创建、维护属于自己的一个架构Demo。
使用的技术栈有：retrofit + hint + coroutine + flow + databinding + viewmodel + lifecycle + livedata + room
遵循 以下的架构模式                                      （本地数据源）
   (UI层)       (ViewModel层)      (仓库层)          / UserLocalDataSource  -> Room
MainActivity -> MainViewModel  -> UserRepository -      （网络数据源）
                                                    \ UserRemoteDataSource  -> Retrofit    -> UserApiService
 

                                                        / LoginLocalDataSource   -> Room
LoginActivity -> LoignViewModel  -> LoginRepository  -    
                                                        \ LoginRemoteDataSource  -> Retrofit  -> LoginApiService  

https://blog.csdn.net/guolin_blog/article/details/109787732?spm=1001.2014.3001.5501