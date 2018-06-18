# test
以宠物进行社交的后台代码  
实现了部分目标功能  

参考Spring实战（第4版）, 以spring为基础使用java配置  

数据库部分   
  数据源 实验了jndi配置，jdbc配置，内置的h2，目前使用内置的h2数据库  
  数据库操作  JDBCTemplate  
  
数据交换  
  使用http协议  
  使用gson将 自定义返回格式Result <DATE_TYPE> 转化为json  
  
用户认证  
  随机数作为token  
  
接口  

    post /login
    登陆   
    参数 json格式 User  
    返回 json格式 Result<>  
    
    post /query  
    查询宠物消息  
    参数 head处添加token  
    返回 json格式 Result<java.util.List<Pet> >  
    
    post /addPet
    添加宠物消息
    参数 head处添加token
    返回 json格式 Result<java.util.List<Pet> >
    
    post /findNearBy
    查找附近一千米用户
    参数 head处添加token
    返回 json格式 Result<java.util.List<User> >
    
    post /showFriend
    显示好友
    参数 head处添加token
    返回 json格式 Result<java.util.List<User> >
    
    post /deletePet
    删除宠物
    参数 head处添加token
    返回 json格式 Result<Long> data部分为空
