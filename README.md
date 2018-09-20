# EventBus
事件总线，轻松实现线程间通讯、组件间通讯。

阿里Android开发手册中提到，Activity之间大数据传递应该避免用Intent装载，容易报`TransactionTooLargeException`异常。
因此打算用这个EventBus来实现。

#### 一、引入`EventBus`库

```java
    implementation 'org.greenrobot:eventbus:3.1.1'
```

#### 二、定义特定事件
```java
/**
 * 特定具体的事件
 * @author Lisa
 * @date 2018/9/19
 */
public class SpecificEvent {

    public String userName;
    public String userAvatar;

    public SpecificEvent(String userName,String userAvatar){
        this.userAvatar = userAvatar;
        this.userName = userName;
    }
    
}

```

#### 三、在订阅者Activity添加EventBus注册、反注册和消息接收器
```java
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isFinishing()&&EventBus.getDefault().isRegistered(this)){
            Log.d(TAG,"onStop");
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 消息接收器
     * @param specificEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(SpecificEvent specificEvent) {
        Toast.makeText(this, "收到消息", Toast.LENGTH_SHORT).show();
        tvReceivedMsg.setText("收到消息："+ specificEvent.userName+" "+specificEvent.userAvatar);
    }
```

#### 四、在消息发布者Activity发布消息
```java
btnPublishMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpecificEvent specificEvent = new SpecificEvent("Lisa","iamgeUrl");
                EventBus.getDefault().post(specificEvent);
                PublisherActivity.this.finish();
            }
        });
```

#### 五、关于EventBus与RxBus的对比
参考资料[RxBus真的能替代EventBus吗？](https://www.jianshu.com/p/669eda5dc5a4)
文中从几个指标来对EventBus、RxBus进行审评
+ 是否容易订阅事件
+ 是否容易发送事件
+ 是否能方便的切换线程
+ 性能

并得出结论：
EventBus以上标准都合格，而RxBus存在事件分发的效率问题。
所以我决定（2018.09.20）新项目暂时考虑选择用EventBus
