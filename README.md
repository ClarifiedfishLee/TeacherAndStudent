# 互动教学系统



## 1、项目概述

本次实验设计并实现一个多终端互动的教务系统，该系统具有通用的基础功能，简洁的界面设计，友好的用户交互，较高的稳定性和安全性。该系统主要服务于大学教师和学生

该系统主要实现一个教师发布课程，并可以发布相应课程的习题；学生可以进行选课退课，并回答已选课程习题的功能。

对于小组成员，通过体验从0到1的移动互联网应用的开发，熟悉包括获取和分析需求、设计框架、存储和传输数据、测试与发布系统等各个开发环节和流程，熟悉各个模块的设计和实现方法，增强对开发工具使用的熟练度，培养基本的全过程开发移动互联网应用的能力与交流协助能力。

## 2、主要业务流程分析

### 2.1登录

![image-20230303113950281](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303113950281.png)

图2.1.1 用户登录流程图

### 2.2课程管理

教师可以发布课程，并可以管理自己已发布的课程；学生可以看到教师发布的课程，并进行选课或退选。

### 2.3答题互动

教师可以在自己的发布的课程里发布习题，并且能够看到所有学生的回答；学生能够看到已选课程中教师发布的习题，并进行回答，此外也可以看到该题所有同学的回答。（类似于评论区）

## 3、需求分析

### 3.1系统用户

用户有两类人，分别是学生和教师。

### 3.2功能需求

（1）共用功能

| 1    | 注册信息     |
| ---- | ------------ |
| 2    | 查看个人信息 |
| 3    | 修改个人信息 |
| 4    | 查看课程信息 |
| 5    | 查看题目回答 |

  （2）教师：

| 1    | 添加课程     |
| ---- | ------------ |
| 2    | 删除课程     |
| 3    | 查看选课学生 |
| 4    | 添加课程题目 |

（2）学生：

| 1    | 选课         |
| ---- | ------------ |
| 2    | 退选         |
| 3    | 回答课程问题 |

系统用例图如下：

![image-20230303114048168](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114048168.png)

 图3.2.1 系统用例图

## 4、系统功能设计

### 4.1登录和注册功能

（1）已注册用户输入正确的用户名和对应的密码能成功登录账号；

（2）教师账号登录成功进入教师页面，学生账号登录成功进入学生页面；

（3）未注册的用户可以点击注册按钮注册，若账号冲突则注册失败；

（4）教师注册需要与教务处提供的名单对比核验（该功能未实现）。

### 4.2课程管理

（1）教师维护自己的课程

教师可以创建课程的信息（名称，学分，学时安排，地点，最多选课人数，时间，课程号）并录入系统；

教师可以查看、修改、删除已经创建的课程。

（2）学生维护自己的课程

学生查看已经创建的课程，容量未满的课程可以选课，已满的课程不能选课；

学生可以查看，退选自己已经选择的课程。

### 4.3答题互动

（1）教师发布习题功能

教师可以在自己已经发布的课程下面发布习题；

教师可以查看习题回答的内容并打分；

系统生成每道题学生回答情况统计图表，包括已回答与未回答同学占比的饼状图，已回答和未回答同学的名单；

系统生成每节课学生回答情况统计图表，包括每位同学应回答题目数与未回答题目数，题目正确率排序，题目完成率排序等。

  （2）学生回答功能

学生可以回答已选课程下的习题；

学生提交自己的答案后可以查看其他回答者的答案。

## 5、系统UI设计

### 5.1登录及注册

主要使用ConstraintLayout布局。

对于首次登录的用户，可以点击登录界面的注册按钮进行注册，输入账号（学号or工号）、密码、姓名、性别即可完成注册。

![image-20230303114129365](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114129365.png)

图5.1.1 注册UI界面

对于已有账号的用户，输入账号和相应密码即可完成登录，此时会弹出AlertDialog向用户确认是否登录。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114146566.png" alt="img" style="zoom:50%;" /><img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114206335.png" alt="image-20230303114206335" style="zoom:50%;" />

图5.2.1 登录UI界面

### 5.2个人信息界面

主要使用ConstraintLayout布局。

在个人信息界面主要显示用户的账号、姓名及性别，点击退出登录会来到登录界面。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114240839.png" alt="image-20230303114240839" style="zoom:50%;" />

图5.2.1 个人信息UI界面

点击修改后，进入修改个人信息UI界面，此时我们可以对姓名和性别进行修改，进入后姓名栏里显示用户的原本姓名，用户可以在此基础上加以修改。修改性别采用了 PopupWindow的设计，点击修改性别按钮，会弹出RadioButton供用户选择。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114303473.png" alt="img" style="zoom:50%;" />

图5.2.2 修改个人信息UI界面

点击修改密码按钮，来到修改密码UI界面，用户可以输入一次旧密码并输入两次新密码来修改密码，密码修改完毕后会强制转到登录界面，需要用户重新登陆。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114327123.png" alt="img" style="zoom:50%;" /><img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114342820.png" alt="image-20230303114342820" style="zoom:50%;" />

图5.2.3 修改密码UI界面

### 5.3主界面UI

主要使用ConstraintLayout布局。

教师端和学生端的主界面UI布局一样，唯一的区别就是Toobar中的title不同。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114414717.png" alt="img" style="zoom:50%;" /><img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114431097.png" alt="image-20230303114431097" style="zoom:50%;" />

图5.3.1主界面UI

### 5.4 教师端课程管理UI界面

教师端点击主界面的课程管理可以进入课程管理界面，里面主要显示自己发布的课程。主要使用LinearLayout和ListView布局



![image-20230303114518067](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114518067.png)

图 5.4.2 具体的课程管理UI展示结果

点击右上角的添加按钮，可以添加课程，输入课程的名称，学分，学时安排，地点，最多选课人数，时间，课程号即可添加。Ps：课程号不能重复。

![image-20230303114531505](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114531505.png)

图5.4.3 添加课程UI界面

点击相应的课程条目，可以进入课程详情课程详情界面，点击课程详情界面的删除本课按钮，可以对课程进行删除。点击学生表单右侧箭头，可以看到所有的选课学生。

![image-20230303114549842](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114549842.png)

图5.4.4 课程详情UI界面



### 5.5 学生端课程管理UI界面

学生端点击主界面的课程管理可以进入课程管理界面，里面主要显示自己已选的课程。主要使用ConstraintLayout和RecycleView布局，点击下方刷新按钮可以对界面进行刷新。



![image-20230303114731528](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114731528.png)

图5.5.2 学生端课程管理UI界面具体展示

点击添加进入选课界面，设计与我的课程界面相同，只是右下角按钮变为选课，这个界面展示所有的未选课程，选过的课不会再出现在这个界面。



### 5.6教师端课程交流界面

教师端点击课程交流进入课程交流界面，首先进入的UI界面布局同教师端课程管理的UI界面，显示的是教师的全部课程。

![image-20230303114832255](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114832255.png)

点击相应条目进入课程习题UI界面，教师端的课程习题UI界面做成了类似于微信首页的由VeiwPager2 和Fragment组成的可滑动式界面，分为习题和发布两个fragment。习题的fragment展示的所有已发布的习题。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114845131.png" alt="img" style="zoom:50%;" /><img src="C:/Users/ljy/AppData/Roaming/Typora/typora-user-images/image-20230303114856929.png" alt="image-20230303114856929" style="zoom:50%;" />

图5.6.2 课程习题UI界面

点击发布的fragment里的发布问答按钮，进入发布问答界面，可以发布习题。

![image-20230303114914581](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114914581.png)图5.6.3 发布习题UI界面

点击习题的fragment相应条目可以看到该题的所有回答情况，顶端是习题内容，下面ListView展示所有学生的回答。

![image-20230303114931668](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114931668.png)

图5.6.4 回答情况UI界面

点击相应回答条目，可以查看具体回答

![image-20230303114946210](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303114946210.png)

图5.6.5 回答详情UI界面

### 5.7学生端课程交流界面

学生端点击课程交流进入课程交流界面，首先进入的UI界面布局同教师端课程管理的UI界面，显示的是学生已选的全部课程。

点击相应条目来到课内问题UI界面，学生端的课内问题UI界面用的是LinearLayout和ListView布局。



![image-20230303115024303](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115024303.png)

图5.7.3课内问题UI界面具体展示

点击我来回答，进入回答的UI界面，学生可以提交答案

![image-20230303115044784](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115044784.png)

图5.7.4 答题UI界面

点击所有回答，来到回答情况UI界面，具体设计同教师端，这里就不冗余介绍了。

<img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115054410.png" alt="img" style="zoom:50%;" /><img src="https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115104304.png" alt="image-20230303115104304" style="zoom:50%;" />

图5.7.5 回答情况UI界面以及点击相应条目后的界面（同教师端）

## 6、系统数据库设计

### 6.1本地数据库设计

#### （1）自动登录

客户端`AutoLoginStatic`类用于保存上次登录使用的账号、密码以及身份（教师or学生），其中使用`SharedPreferences`变量以key-value形式来存储上述三个内容。分别以String、String、boolean类型存储。涉及到的方法如下

| `public void  setPassword(String password , Context context)  ` | 设置存入的密码     |
| ------------------------------------------------------------ | ------------------ |
| `public void  setTeacher(boolean isteacher,Context context)` | 设置身份           |
| ` public void  setUserNum(String usernum, Context context)  ` | 设置存入的账号     |
| `public String  getUserNum(Context context)  `               | 得到上次存入的账号 |
| `public String  getPassword(Context context)  `              | 得到上次存入的密码 |
| ` public boolean  isTeacher(Context context)  `              | 得到上次存入的身份 |

在登录Activity中，每次登陆前都会取出SP中的内容先与远端数据库中内容比较，通过后可自动登录，若没有通过则需要输入账号密码进行登录。也就是说，只有在联网状态下才可以登录本系统。

#### （2）用户信息数据库

客户端MySQLIteHelper类保存的是用户信息，不过其实并不需要这个数据库，但后续修改较为麻烦故保留。

数据库格式如下：

| 名称     | 用途                     | 存储格式                           |
| -------- | ------------------------ | ---------------------------------- |
| _id      | 自增键                   | integer primary key  autoincrement |
| account  | 账号                     | text                               |
| password | 密码                     | text                               |
| name     | 姓名                     | text                               |
| gender   | 性别（1为男，0为女）     | integer                            |
| job      | 身份（1为教师，0为学生） | integer                            |

方法：

| `public void  insertUser(Me user) `                 | 添加一个用户                                 |
| --------------------------------------------------- | -------------------------------------------- |
| `public Cursor  query(String sql, String[] args)  ` | 查询，返回查询用到的cursor                   |
| `public void update(Me  user)  `                    | 更新用户信息，如果用户不存在则插入到数据库中 |

#### （3）课程信息数据库

客户端`ClassesSQLIteHelper`类存储课程信息，格式如下

| 名称           | 用途           | 存储格式                           |
| -------------- | -------------- | ---------------------------------- |
| _id            | 自增键         | integer primary key  autoincrement |
| classid        | 课程号         | text                               |
| teacheraccount | 开课教师的账号 | text                               |
| classname      | 课程名称       | text                               |
| classnum       | 课时数         | integer                            |
| credit         | 学分           | real                               |
| location       | 上课地点       | text                               |
| time           | 上课时间       | text                               |
| maxstunum      | 课程容量       | integer                            |
| true_stunum    | 真实选课人数   | integer                            |

方法：

| ` public void  insertClass(Class clas)  `                    | 向数据库中插入一门课               |
| ------------------------------------------------------------ | ---------------------------------- |
| `public Cursor  query(String sql, String[] args)  `          | 查询，返回查询用到的cursor         |
| ` public void  deleteOne(String class_id )  `                | 删除数据库中的某门课               |
| `public void  update(List<Class> classList,Context context)  ` | 依照传入的List更新数据库中所有课程 |

其中最后的更新涉及到的逻辑较为复杂：

1.首先根据传入的list获得一个classid的list单

2.之后遍历本地数据库：如果本地数据库有，list单中也有，则不管，并从list单中删除该classid。

​           如果本地数据库中有，而list单中没有，则删除本地数据库中符合classid的内容。

3.遍历结束后，list单中剩下的都是本地数据库中没有的课程，此时根据list单中

的内容，从最初传入的class的lsit中选出相应课程，加入到本地数据库中。

这个更新目的是报纸本地数据库和远端数据库中内容的一致。

代码如下：

```java
public void update(List<Class> classList,Context context){
    List<Class> classes = new ArrayList<>(classList);
    List<String> classidList = new ArrayList<>();
    for(Class cla : classes){//从远端数据库中获得的清单
        classidList.add(cla.getClass_id());
    }

    SQLiteDatabase db = this.getWritableDatabase();
    if(db.isOpen()) {
        String cursor_query = "select classid from classes";
        Cursor cursor = db.rawQuery(cursor_query, null);
        int size = cursor.getCount();
        int index = cursor.getColumnIndex("classid");
       //若本地数据库有，则不管，若本地数据库有而远端没有，则删除
        for (int i=0;i<size;i++){
            cursor.moveToNext();
            String class_id = cursor.getString(index);
            if(classidList.contains(class_id)){
                classidList.remove(class_id);
            }else{
                String deleSQL = "delete from classes where classid=?";
                db.execSQL(deleSQL,new Object[]{class_id});
            }
        }
        cursor.close();
        db.close();
        //把剩下的加进本地数据库
        if(classidList.size()>=1) {
            List<Class> classList2 = new ArrayList<>();
            for(Class cla : classes){
                if(classidList.contains(cla.getClass_id())){
                    classList2.add(cla);
                }
            }
            for(Class cla : classList2){
                ClassesSQLIteHelper.getInstance(context).insertClass(cla);
            }
        }

```



#### （4）选课信息数据库

客户端`ChooseClassSQLIteHelper`类用于存放学生选课，类似于一个目录。

数据库格式如下：

| 名称        | 用途         | 存储格式                          |
| ----------- | ------------ | --------------------------------- |
| _id         | 自增键       | integer primary key autoincrement |
| classid     | 课程号       | text                              |
| stu_account | 选课学生账号 | text                              |

方法：

| `public String  insertChoice(String class_id , String stuid)  ` | 插入选课                                                     |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| `  public  List<Class> getAll(String stu_account,Context context)  ` | 与`ClassesSQLIteHelper`联动，得到学生的所有选课              |
| `  public String deleteOne(String  class_id , String stu_id)  ` | 删除学生选课                                                 |
| ` public void  update(List<Class> classes, String stu_id, Context context)  ` | 更新学生选课，同时更新`ClassesSQLIteHelper`中存储的class。更新逻辑同`ClassesSQLIteHelper`类的更新方法。 |

### 6.2远程数据库设计

远程数据库选择MySQL系统。总体的物理ERD设计如下：

![image-20230303115130429](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115130429.png)

图 6.2.1 远程数据库物理ERD设计

#### （1）账号信息表

![image-20230303115137497](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115137497.png)

图 6.2.2 账号信息表设计

将老师和学生账号存放在同一表上，用isteacher表项来区分，方便数据存储和查找。登录时通过此表检索账号和密码信息是否匹配，查看个人信息时通过此表检索账号对应的个人信息，注册时检测账号不冲突后在此表添加数据。

#### （2）课程信息表

![image-20230303115147779](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115147779.png)

图6.2.4 课程信息表设计

课程信息以课程ID作为主键，教师ID为外键以维护教师创建课程的信息，存放了课程名称、教师名称、课时数、学分、最大选课人数、实际选课人数、上课时间、上课地点等信息。

#### （3）选课信息表

![image-20230303115156506](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115156506.png)

![image-20230303115203216](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115203216.png)

图6.2.5 选课信息表设计

选课信息以自增的id为主键，课程id和账号id分别为两个外键以维护学生的选课信息。

因为一位学生可以选择多个课程，且一个课程可以供多个学生选择，所以On Update的约束关系选择NO ACTION。

#### （4）问题信息表

![image-20230303115210762](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115210762.png)

图6.2.6 问题信息表设计

问题信息表以自增的问题id作为主键，一个问题仅对应一门课程，故而课程id作为外键，此外存放问题发布时间和问题内容。

#### （5）答案信息表

![image-20230303115217983](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115217983.png)

图6.2.7 答案信息表设计

答案信息表以自增的答案id作为主键，一个答案由一位同学回答且仅针对一个问题，故而以学生id和问题id作为外键，此外存放答案发布时间，回答者姓名，答案内容。

#### （6）统计信息表

![image-20230303115225606](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115225606.png)

图6.2.8 统计信息表设计

选课信息以自增的id为主键，问题id和学生id分别为两个外键以维护学生的回答问题信息。Mark记录教师打分和评语。该表用于统计学生回答问题的情况并形成分析图表供教师查看，但实际并未实现该功能。

## 7、系统体系结构设计

系统结构如下：



![image-20230303115322582](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115322582.png)

图7.1 系统结构

### 7.1客户端活动设计

共设计了20个活动继承自定义的MyActivity和诸多Adapter，实现客户端表示层的各个功能和客户端处理层部分逻辑处理的功能。

![image-20230303115338793](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115338793.png)

图 7.1.1 客户端活动类

### 7.2客户端数据设计

设计了7个数据类和四个数据连接辅助类实现客户端处理层数据校验和本地数据存取等功能。详见6.1本地数据库设计。

![image-20230303115349806](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115349806.png)

图 7.2.1 客户端数据类

![image-20230303115403423](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115403423.png)

图 7.2.2 客户端数据连接类

### 7.3客户端其他辅助类

fragment。

![image-20230303115413689](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115413689.png)

图 7.3.1 

### 7.4客户端与服务器通信

客户端与服务器通信使用的是Http协议，传输json进行交流。具体内容放在介绍统一接口设计时介绍。

各个Activity中通过开启新线程，调用接口中的方法，通过message和handler联动来实现和服务器的交互。因为所有的调用逻辑大同小异，下面以teacheractivity包中的myclassesActivity这个Activity为例。

线程类：`MyClassesFromRemote`，从远端获取该教师开设的全部课程，首先通过接口sv从服务器获得`list<Class>`，然后包装进message的obj变量中，将message的what值设置为1，然后发送给handler

```java
public void MyClassesFromRemote(){
        new Thread(new Runnable() {
            @Override
            public void run() {
     List<Class> classList = sv.getClasses(MyURL.TeaURL,true,Teaid,false,null);
                Message msg =myHandler.obtainMessage();
                msg.obj = classList;
                msg.what = 1;
                myHandler.sendMessage(msg);
            }
        }).start();
    }

```

Handler类：`MyHandler`，主要使用` public void handleMessage(Message msg) `方法，获得课程的list，加入到`ListAdapter`中展示，并通过`ClassesSQLIteHelper`的update方法更新本地数据库。

```java
private class MyHandler extends Handler {
        //弱引用持有HandlerActivity , GC 回收时会被回收掉
        private WeakReference<myclassesActivity> weakReference;
        public MyHandler(myclassesActivity activity) {
            this.weakReference = new WeakReference(activity);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<Class> classes = (List<Class>) msg.obj;
            switch (msg.what) {
                case 1:
                    list.addAll(classes);
                    classListAdapter = new ClassListAdapter(context,list);
                    listView.setAdapter(classListAdapter);     
// 更新数据库
             ClassesSQLIteHelper.getInstance(context).update(list,context);
                    break;
            }
        }
    }

```

### 7.5服务器活动设计

![image-20230303115432149](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115432149.png)

图 7.5.1 服务器活动设计

服务器设计4个servlet继承自定义的`MyServlet`，分别用来处理客户端发来的注册登录相关请求，教师创建课程相关请求，学生选课退选相关请求以及问题和回答相关请求。`MyServlet`继承`HttpServlet`类，添加了`getRequestJson`函数，用于预处理从客户端获得的信息，生成统一的json格式，方便后续的逻辑处理。

![image-20230303115440653](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115440653.png)

图 7.5.2 MyServlet类

4个servlet处理方式大同小异，在doPost函数中，准备数据库，调用getRequestJson函数处理请求，根据请求代码做对应的逻辑处理并在相应json中填入客户端需要的信息。以问题回答相关的QA Servet为例，代码如下：

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //准备数据库
AccountBaseDao accountBaseDao = new AccountBaseDao();
AnswerBaseDao answerBaseDao = new AnswerBaseDao();
QuestionBaseDao questionBaseDao = new QuestionBaseDao();
CourseBaseDao courseBaseDao = new CourseBaseDao();
        //开始处理请求
JSONObject requestJson = getRequestJson(request);
System.out.println("收到请求："+requestJson.toString());
int Method = (int)requestJson.get("code");
JSONObject res = new JSONObject();
        //教师添加某一门课的问题
if(Method==14){
res.put("code",14);
System.out.println("教师添加某一门课的问题");
Object[] questionInfo = {
requestJson.get("classId"),requestJson.get("time"),requestJson.get("questionText")};
int i = questionBaseDao.addQuestion(questionInfo);
if(i==1){
response.setStatus(200);
}else{
response.setStatus(400);
}
}
if (Method==15) {
//查询某一门课的全部问题
res.put("code",15);
System.out.println("查询某一门课的全部问题");
List<Question> allQ;
Object[] classId = {requestJson.get("classId")};
allQ = new ArrayList<>(questionBaseDao.queryQuestion(classId));
            res.put("question",new JSONArray(allQ));
} else if (Method==17) {
//查询一个问题的全部回答
res.put("code",17);
System.out.println("查询一个问题的全部回答");
List<Answer> allA;
Object[] questionId = {requestJson.get("questionId")};
allA = new ArrayList<>(answerBaseDao.queryAnswer(questionId));
            res.put("question",new JSONArray(allA));
        } else if (Method==18) {
            //学生提供一个问题的答案
res.put("code",18);
System.out.println("学生提供一个问题的答案");
Object[] answerInfo = {
requestJson.get("stuId"),requestJson.get("questionId"),requestJson.get("time"),requestJson.get("answerText"),null};
 Object[] stuId = {requestJson.get("stuId")};
 String stuName = null;
try{
stuName=accountBaseDao.queryNameById(DBUtil.getAccountTableName(),stuId);
}catch (Exception e){
System.out.println("查询学生姓名失败");
e.printStackTrace();
}
answerInfo[4] = stuName;
int i = answerBaseDao.addAnswer(answerInfo);
if(i==1){
response.setStatus(200);
}else{
response.setStatus(400);
}
}
String resStr = res.toString();
        System.out.println("返回响应："+resStr);
        response.getWriter().append(resStr).flush();
    }
  
```

### 7.6服务器数据设计

![image-20230303115454096](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115454096.png)

图7.6.1 服务器数据类设计

服务器端设计了5个Entity对应6.2节远程数据库的各个表，并为每个Entity设计了具体的`BaseDao`实现自定义的`BaseDao`抽象类，用于处理对应表格数据的增删改查。设计`DBUtil`通过JDBC连接MySQL数据库。

`BaseDase`抽象类除了构造函数外只有两个函数，其中`updateDate`通过传入sql命令和Object参数，实现对数据的增删改，返回修改数据的条数；query通过传入sql命令，Object参数和目标实体的class，实现对数据的查询。5个具体的`BaseDao`大同小异，基于实现了更便于使用的增删改查功能，如`AccountBaseDao`有通过查询id修改对应的姓名性别等信息，`AssociationBaseDao`有删除课程id为x的所有信息等等在此不一一赘述。

## 8、统一接口设计

### 8.1客户端与服务器的接口

客户端`WebService`的`ServerService`类里有全部的与服务器交流使用的接口方法。与服务器的交流使用的是Http协议，主要用到的是`HttpURLConnection`，以及相应方法，通过`OutputStream`以json格式向服务器发送请求，每个json格式都会含有有一个key-value对，为”code”- integer对，代表客户端的需求。然后以`inputStream`来接收服务器传回来的建json文件，并对其解析来获得客户端想要的内容。

每个方法都需要传入服务器的URL，下方就不对这个传入参数过多介绍。

（1）登录请求，方法名如下

`public List<Boolean> LoginQuery(String user_account,String user_password,String url_str)`

传入的参数有用户账号、密码。传给服务器的json格式中的key以及其含义如下：

| code     | 1    | 1号请求  |
| -------- | ---- | -------- |
| account  |      | 用户账号 |
| password |      | 用户密码 |

会对得到的json文件进行解析，判断账号密码是否正确，以及用户的身份是学生还是教师，返回格式是一个布尔的list，其中list(0)代表账号密码是否正确，list(1)代表用户身份，list(2)代表此次连接是否成功。

（2）个人信息查询，方法名如下

`public Me MessageQuery(String account, String url_str)`

传入的数据有用户账号。传给服务器的json格式中的key以及其含义如下：

| code    | 2    | 2号请求  |
| ------- | ---- | -------- |
| account |      | 用户账号 |

解析传回的json文件后，封装到数据结构Me当中，作为返回值。数据结构

Me格式如下：

![image-20230303115842571](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115842571.png)

​      图8.1.1 Me数据结构内容

（3）注册功能，方法名如下：

`public String regToRemote(Me me,String url_str)`

传入的参数为Me，上方有对这个数据结构的介绍，这里就不过多介绍了。传给服务器的json格式中的key以及其含义如下：

| code     | 3    | 3号请求  |
| -------- | ---- | -------- |
| account  |      | 用户账号 |
| password |      | 用户密码 |
| name     |      | 姓名     |
| gender   |      | 性别     |
| job      |      | 身份     |

接收内容只判断其返回码，如果是200，代表注册成功，返回true；否则代表注册失败，返回false。

（4）教师添加课程，方法名如下

`public String AddClassToRemote(Class the_class,String url_str)`

传入的参数为Class，该数据结构如下：

![image-20230303115856887](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115856887.png)

​           图8.1.2 Class数据结构

传给服务器的json格式中的key以及其含义如下：

| code      | 4    | 4号请求      |
| --------- | ---- | ------------ |
| classid   |      | 课程号       |
| teacherid |      | 教师账号     |
| classname |      | 课程名       |
| classnum  |      | 课时数       |
| maxnum    |      | 最大选课人数 |
| credit    |      | 学分         |
| location  |      | 上课地点     |
| schedule  |      | 上课时间     |

接收内容只判断其返回码，如果是200，代表添加成功，返回true；否则代表添加失败，返回false。

（5）删除课程及选课，方法名如下

`public boolean DeleteMyClass(String class_id, String url_str,boolean isTea,String stu_id,boolean selected)`

传入参数有课程号，身份，学生账号，以及学生操作。当身份isTea为true时，代表教师删除课程；当isTea为false时，判断selected的值，当selected为true代表学生选课，当selected为false时，代表学生退选。传给服务器的json格式中的key以及其含义如下：

教师删除课程：

| code    | 5    | 5号请求 |
| ------- | ---- | ------- |
| classid |      | 课程号  |

学生删除课程：

| code    | 6    | 6号请求  |
| ------- | ---- | -------- |
| classid |      | 课程号   |
| stuid   |      | 学生账号 |

学生选课：（没错code就是16，之前调试时一直有问题，就改了一下code，但后来发现是URL出错了，最后这里并没有修改，而是用了最后一次使用的16作为code码，算是对这里出错那么多次，改了那么久的一个小纪念）

| code    | 16   | 16号请求 |
| ------- | ---- | -------- |
| classid |      | 课程号   |
| stuid   |      | 学生账号 |

接收内容只判断其返回码，如果是200，代表操作成功，返回true；否则代表操作失败，返回false。

（6）获取课程，方法名如下：

`public List<Class> getClasses(String url_str,boolean isTea,String teacher_id,boolean stu_chosen, String stuid)`

传入的参数有身份、教师账号、操作、学生账号。当身份isTea为true时，代表教师拉取所有自己开创的课程；当isTea为false是，看操作stu_chosen，当stu_chosen为true时代表学生已选的课程，此时要拉取学生选过的课程下来；当stu_chosen为false时代表学生选课，此时要拉取所有其他未被选过的课程下来。传给服务器的json格式中的key以及其含义如下：

学生选课获取课程列表：

| code  | 7    | 7号请求  |
| ----- | ---- | -------- |
| stuid |      | 学生账号 |

学生获取已选课程列表：

| code  | 8    | 8号请求  |
| ----- | ---- | -------- |
| stuid |      | 学生账号 |

教师获得自己开设的课程列表：

| code      | 9    | 9号请求  |
| --------- | ---- | -------- |
| teacherid |      | 教师账号 |

传回的内容是json组，里面包含课程信息，将每个课程封装到Class数据结构中并加入到list里，作为方法返回内容。（Class数据结构上方有介绍，这里就不赘述了）

（7）获得选课学生表单，方法名如下：

`public List<Student> StuChosenClass(String classid,String url_str)`

传入参数为课程号，传给服务器的json格式中的key以及其含义如下：

| code    | 11   | 11号请求 |
| ------- | ---- | -------- |
| classid |      | 课程号   |

传回内容为学生账号、姓名、性别组成的json组，将每个学生封装到Student数据结构中并加入到list里，作为方法返回内容。Student数据结构详情如下：

![image-20230303115910969](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115910969.png)

​           图8.1.3 Student数据结构

（8）修改个人信息，方法名如下：

`public boolean ChangeMess(String user_id,String url_str,boolean ispassword,String new_pass,String new_name,Boolean genderChange ,Boolean new_Gender)`

传入参数有用户账号、操作（修改密码or个人信息）、新密码、新名字、

性别是否更改、新性别。当操作`ispassword`为true时代表改密码，false是代表修改个人信息，此时当`genderChange`为true时代表性别发生更改，false时代表没有修改性别。传给服务器的json格式中的key以及其含义如下：

修改密码：

| code     | 12   | 12号请求 |
| -------- | ---- | -------- |
| account  |      | 用户账号 |
| password |      | 新密码   |

修改个人信息：

| code          | 13   | 13号请求             |
| ------------- | ---- | -------------------- |
| name          |      | 新名字               |
| account       |      | 用户账号             |
| gender_change |      | 性别是否修改的判断位 |
| gender        |      | 新性别               |

接收内容只判断其返回码，如果是200，代表操作成功，返回true；否则代表操作失败，返回false。

（9）添加习题，方法名如下：

`public boolean SendQues(Question ques,String url_str)`

传入参数为Question，这个数据结构详细内容如下：

![image-20230303115919494](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115919494.png)

​        图8.1.4 Question数据结构

传给服务器的json格式中的key以及其含义如下：

| code         | 14   | 14号请求     |
| ------------ | ---- | ------------ |
| classId      |      | 课程号       |
| time         |      | 习题发布时间 |
| questionText |      | 习题内容     |

接收内容只判断其返回码，如果是200，代表发布问题成功，返回true；否则代表发布问题失败，返回false。

（10）获取某门课的全部问题，方法名如下：

`public List<Question> getQuestion(String classId,String url_str)`

传入参数为课程号，传给服务器的json格式中的key以及其含义如下：

| code    | 15   | 15号请求 |
| ------- | ---- | -------- |
| classId |      | 课程号   |

传回的内容是json组，里面包含问题信息，将每个问题封装到Question数据结构中并加入到list里，作为方法返回内容。（Question数据结构上方有介绍，这里就不赘述了）

（11）获得某个问题的全部回答，方法名如下：

`public List<Answer> getAnswer(int quesId,String url_str)`

传入参数为问题编号，传给服务器的json格式中的key以及其含义如下：

| code       | 17   | 17号请求 |
| ---------- | ---- | -------- |
| questionId |      | 问题编号 |

传回的内容是json组，里面包含回答信息，将每个问题封装到Answer数

据结构中并加入到list里，作为方法返回内容。Answer数据结构详情如下

![image-20230303115928394](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115928394.png)

​        图8.1.5 Answer数据结构

（12）提交答案，方法名如下：

`public boolean SendAnswer(Answer answer , String url_str)`

传入参数为Answer（Answer数据结构上方有介绍，这里就不赘述了），传给服务器的json格式中的key以及其含义如下：

| code       | 18   | 18号请求 |
| ---------- | ---- | -------- |
| stuId      |      | 学生账号 |
| questionId |      | 问题编号 |
| time       |      | 回答时间 |
| answerText |      | 回答内容 |

接收内容只判断其返回码，如果是200，代表回答成功，返回true；否则代表回答失败，返回false。

## 9、详细设计与成果分析

这里主要给出一些开发时的小设计，以及心得。

### 9.1客户端Activity的管理

各个Activity需要进行统一的管理，来控制他们的生命周期，比如登录后，登录界面的Activity应该被finish，修改密码后，应该打开登录的Activity而关闭所有其他Activity。因此创建了 `MyActivity`类和`ActivityCollector`类来管理所有Activity。

`MyActivity`类如下，所有其他创建的Activity均继承`MyActivity`类，该类会在创建时将当前Activity加入到管理集合`ActivityCollector`类中，便于管理。

![image-20230303115957915](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303115957915.png)

`ActivityCollector`类的方法如下：

| `public static void  finishOneActivity(String ActivityName)  ` | 关闭传入的Acticvity                      |
| ------------------------------------------------------------ | ---------------------------------------- |
| ` public static void  finishOtherActivity(String ActivityName)` | 只留下传入额Activity而关闭其他的Activity |

### 9.2各个Activity之间的信息传递

用bundle实现在各个Activity中的信息传送，以key-value 形式传送。传送逻辑一样，所以只举出下面这个例子，除下面例子外，项目中很多地方都用到了这种信息传递机制。

比如课程信息详情，点击进入课程详情时，再从数据库中读取课程内容太过麻烦，我们就直接在课程列表的Activity中把相关数据通过bundle传到课程详情中的Activity中，避免了对数据库的多次访问。

下图便是将课程信息封装到bundle中，在将bundle通过`intent.putExtra`来放进intent中进行传递。

![image-20230303120024201](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120024201.png)

​          图9.2.1 bundle传输数据操作

然后在另一个Activity中通过

`  intent = getIntent();   Bundle bundle =  intent.getBundleExtra("bun");  `

来获得bundle并对其进行解析，具体操作如下图

![image-20230303120037707](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120037707.png)

​         图9.2.2 bundle解析数据操作

### 9.3联网判断

本地数据库的操作为，只要有网络连接就读取远程的数据库内容，当网络断掉时才会读取本地内容。并且每次和远端数据库连接都会更新本地数据库。为此创建了NetJudgeHelper类来判断目前是否连接网络，具体内容如下。

![image-20230303120102248](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120102248.png)

​         图9.3.1 判断网络是否连接的类

以教师端的`myclassActivity`为例，拉取教师开设的全部课程时，用布尔变量`find_in_local`作为是否联网的标志，若联网就不用本地，直接读取远端，并把`find_in_local`置为false，否则`find_in_local`为true，会调用`ClassesSQLIteHelper`来读取本地数据。部分代码示例如下图

![image-20230303120121971](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120121971.png)

 图9.3.2 判断联网后的操作

### 9.4远程数据库设计考量

远程数据库设计有两点经过斟酌考量：

第一是association表，最初用来维护教师创建课程和学生选择课程两种信息，尽管这样于效率没有影响，但考虑到course表中已经包含教师id作为外键，本身起到维护教师创建课程这一信息，不必重复存储，故而将association表定为学生选课表。

第二是course表，因为有重名的可能，一般选择id作为个人账号的标识而不是名字，所以course表中必须有teacherId标识这节课的老师，而查看课程信息时需要显示教师的姓名。此时有两种方案，一种是course表不存储教师姓名，每次需要显示课程时，查询课程对应教师id进而查询教师姓名，将教师姓名和其他课程信息发送给客户端，另一种是course表存储教师姓名，创建课程时根据客户端发来的教师id查询教师姓名，一并存入MySQL数据库。前者增加了每次查询的复杂性，后者则需要注意教师修改姓名时需要找到其创建的所有课程并修改名称。本项目选择后者。

![image-20230303120145381](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120145381.png)

图 9.4.1 对修改姓名的教师修改其创建课程信息

### 9.5 心得体会

（1）张洪实：

通过这次大作业，仔细地了解了Android studio的使用，以及安卓客户端内容的设计，本地数据库的设计，以及Activity的联动和与远端服务器的连接处理机制，收获良多。

（2）李婕妤：

通过为期八周的实验，在唐老师的带领下，我和张洪实同学一起完成了此项“师生互动教学系统”，了解了移动应用程序基本的开发流程，从框架构建，UI设计，数据库设计，网络设计到最后的集成测试一步步实现，掌握了基本的移动应用开发技能。在后续的工作中我愈发明白前期明确的规划和清晰的架构的重要性。开发过程中，我和张洪实同学交流充分，分工明晰，互相帮助，成就了一次愉快的合作。

## 10.其他

（1）客户端修改

运行程序需要修改URL，即在`WebService`包中的MyURL类里将下面内容的http后方的IP地址改为服务器当前IP地址。

![image-20230303120206122](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120206122.png)

图10.1 MyURL类

（2）服务器端配置

下载Tomcat 10.0.27，使用IntelliJ IDEA Ultimate 2022.2.3打开server，配置Run/Debug Configuration

![image-20230303120215471](https://raw.githubusercontent.com/ClarifiedfishLee/image_resources/master/img/image-20230303120215471.png)

图10.2 Run/Debug Configuration配置信息
