# webchat

    此程序通过使用css，spring，html，javascript实现网页对话等功能。 
技术框架:spring+springmvc+mybatis+juqery+ajax，数据库使用mysql  </br>

## 具体功能
>（1）聊天页面。<br>
    * 聊天页面主要显示好友间的对话信息，聊天信息发送框以及发送目标与发送按钮。对话信息中包括发表用户昵称，发表日期、时间以及发送人昵称。对话框外包括对话双方头像的显示。 </br>
    * 聊天页面右侧栏为好友列表。包括好友个数、好友昵称的显示。好友列表中有接收信息的显示以及已添加好友，点击可以进行私聊。 </br>
>（2）个人信息页面。<br>
    * 个人信息中有登录用户的注册信息，包括昵称、性别、出生日期、个人简介、注册时间以及最后登录日期，还有用户头像的显示，点击头像实现头像图片放大显示，支持图片保存下载。</br>
>（3）设置页面。<br>
   * 设置页面包括两个子页面：<br>
   * 个人设置页面：<br>
>>1）用户基本信息。<br>
    * 用户基本信息包括用户名、昵称、验证信息、性别、年龄、个性签名。用户名用户没有权限更改，可以更改昵称；验证信息为对方添加用户时的添加操作反馈信息，是否通过验证同意添加，用户在此页面可以选择“允许加为好友”、“需要验证”、“拒绝添加好友”三种方式；性别选项可以选择“男”或“女”；年龄选项为必填项，最高不超过100；个性签名为用户自由编辑话语用于激励自己或向别人展示自己等等。<br>
    * 用户可以通过此页面实现个人信息的修改，点击提交按钮修改完成。<br>
  2）修改头像信息。<br>
    * 修改头像功能包括对现有头像的查看，选择本地图片文件，点击“上传头像”按钮完成修改。<br>
    * 此页面可以实现用户头像的变更。可在个人信息页面查看修改结果。<br>
>>3）修改密码页面。<br>
    * 此页面包括原密码、新密码、确认新密码，当新密码与原密码不同与确认密码相同时，点击“提交修改”按钮可以完成密码的变更。<br>
>（4）心情动态页面：<br>     
>>1）发表动态：用于发表个人心情动态，包括图片及个性签名的编辑。<br>
>>2）动态列表：查看过去发布动态。<br>
>（5）好友动态页面：<br>
       >> 1）查找好友：通过昵称查找好友或添加推荐好友。<br>
       >> 2）好友动态：查看好友发布动态。<br>
>（6）系统日志：包括此账号的登录信息及登陆时间、IP地址。<br>
>（7）注销：退出此账号登录信息。<br>
>（8）聊天室信息：<br>
>>1）左上角为聊天室logol，右上角为用户信息菜单。<br>
>>2）左下角为聊天室信息。<br>
>>3）最下边栏为开发信息。<br>

--------
### SSM整合框架的运行过程
#### 1.启动TOMCAT服务器
启动tomcat服务器的时候，会加载服务器下webapps中的所有项目<br>
读取web项目中的web.xml文件的内容,读取其中applicationContex.xml，加载spring容器对象,创建了相关的对象(dataSource,SqlSessionFactory,Mapper代理对象 默认规则:接口名称首字母小写就是Mapper对象的ID)<br>
#### 2.访问login.jsp页面,并提交请求
提交表单 action=”chatUser/login.do” <br>
文本框:userid   password<br>
#### 3.chatUser/login.do提交后，会加载DispatcherServlet控制器<br>
第一次启动DispatcherServlet控制器会加载参数(加载spring_mvc.xml,读取base-package包下的所有注解 ,创建相关对象建立关联关系, <br>
@Controller 控制器类对象 @Service 业务类对象 @Repository 数据持久化类<br>
@RequestMapping 请求映射<br>
chatuser/login   、   ChatUserController.login()
chatUser/add    、   ChatUserController.add()<br>
@Autowired 自动装配)<br>
把表单重点参数传递给控制器了形参<br>
#### 4.执行ChatUserController.login(ChatUser user) 方法<br>
如何获取参数：表单中的参数名称等于控制器方法参数或者等于控制器方法参数的属性由 DispatcherServlet 自动传递<br>
Controller类依赖的Biz类，由@Autowired 自动装配其关联对象<br>
BIZ对象调用Mapper对象，并返回结果个控制器<br>
控制器的方法,返回一个字符串(ChatUserController.login方法返回了index字符串)<br>
#### 5.DispatcherServlet控制器接收ChatUserController.login方法返回了index字符串结果,由视图解析器对象，拼接前缀与后缀，新城请求路径:/index.jsp,返回客户端<br>
#### 6.需要注意的地方:<br>
相关的对应数据：<br>
表单的action --对应--        RequestMapping得值<br>
表单的控件名  --对应--      Controller类中的方法参数<br>
Controller 中的 biz属性名   --对应-- @Service(value=”值”)<br>
Controller方法的返回值    --对应了--   JSP的根路径<br>
applicationContext.xml 、 spring_mvc.xml的作用<br>
