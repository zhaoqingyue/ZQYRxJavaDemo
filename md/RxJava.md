### RxJava

开源项目
- https://github.com/ReactiveX/RxJava
- https://github.com/ReactiveX/RxAndroid

导入依赖
```
compile 'io.reactivex:rxjava:1.0.14'
compile 'io.reactivex:rxandroid:1.0.1'
```

**什么是Rx？**

---- Reactive Extensions的缩写。Rx是微软.NET的一个响应式扩展。Rx借助可观察的序列提供一种简单的方式来创建异步的，基于事件驱动的程序。

---- RxJava是Reactive Extensions的Java VM实现：用于通过使用可观察序列来编译异步和基于事件的程序的库。归根到底，就是异步。


**为什么要用RxJava？**

---- 用简洁的流式操作处理各类复杂的异步请求。


**关键字**
- 异步
- 链式调用
- 观察者模式

**基本实现**

![image](https://github.com/zhaoqingyue/ZQYRxJavaDemo/blob/master/img/1.jpg)

基本对象：
- Observable ---- 被观察者
- Observer ---- 观察者
- Subscribe ---- 订阅
- onEvent ---- 事件

基本用法：
1. 创建被观察者(Observable)
2. 创建观察者Observer或Subscriber
3. 订阅事件subscribe

### 1. 创建被观察者(Observable)
```
Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {

    @Override
    public void call(Subscriber<? super String> sub) {
        sub.onNext("1. 创建Observable\n");
        sub.onNext("2. 创建Subscriber\n");
        sub.onNext("3. 关联Observable与Subscriber");
        sub.onCompleted();
    }
});
```
常用到的几种方法：

- creat()：使用一个函数从头创建一个Observable

```
Observable.create(new Observable.OnSubscribe<String>() {

    @Override
    public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("One");
        subscriber.onNext("TWO");
        subscriber.onNext("THREE");
        subscriber.onCompleted();
    }
});
```

- just(T…)：将一个或多个对象转换成发射这个或这些对象的一个Observable

```
Observable.just("One", "TWO", "THREE");
// 将会依次调用：
// onNext("One");
// onNext("TWO");
// onNext("THREE");
// onCompleted();
```

- from(T[])：将一个Iterable,一个Future, 或者一个数组转换成一个Observable

```
String[] test = {"One", "TWO", "THREE"};
Observable.from(test);
// 将会依次调用：
// onNext("One");
// onNext("TWO");
// onNext("THREE");
// onCompleted();
```

- repeat()：创建一个重复发射指定数据或数据序列的Observable

```
Observable.just("One", "TWO", "THREE")
    .repeat(3)
    .subscribe(new Action1<String>() {
    
        @Override
        public void call(String s) {
                    
        }
});
```

- defer()：只有当订阅者订阅才创建Observable；为每个订阅创建一个新的 Observable

```
Observable.defer(new Func0<Observable<String>>() {

    @Override
    public Observable<String> call() {
        return Observable.just("One");
    }
});
```

- range()：创建一个发射指定范围的整数序列的Observable

```
// 依次发射 1, 2, 3, 4, 5
Observable.range(1, 5)
```

- interval()：创建一个按照给定的时间间隔发射整数序列的Observable

```
// 每隔 1 s 发送一个序列号，从 0 开始，每次累加 1。
Observable.interval(1, TimeUnit.SECONDS);
```

- timer()：创建一个在给定的延时之后发射单个数据的Observable

```
// 定时 1 s
Observable.timer(1, TimeUnit.SECONDS);
```


### 2. 创建观察者Observer或Subscriber
----Subscriber继承Observer： 定义三个回调方法

```
Observer<String> observer = new Observer<String>() {

    @Override
    public void onNext(String s) {
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }
};
```

- onNext(T item)

---- Observable调用这个方法发射数据，方法的参数就是Observable发射的数据，该方法可能会被调用多次。

- onComplete()

---- 正常终止，如果没有遇到错误，Observable在最后一次调用onNext之后调用此方法。

- onError(Exception ex)

---- 当Observable遇到错误或者无法返回期望的数据时会调用这个方法，这个调用会终止 Observable，后续不会再调用onNext和onCompleted，onError方法的参数是抛出的异常。

根据Observable协议的定义，onNext可能会被调用零次或者很多次，最后会有一次 onCompleted或onError调用（不会同时），传递数据给onNext通常被称作发射， onCompleted和onError被称作通知。

### 3. 订阅事件subscribe: 关联Observable与Observer
```
/**
 * 一旦observer订阅了observable，
 * observable就调用observer对象的onNext和onComplete方法
 */
observable.subscribe(observer) ;
```

### 线程调度器Scheduler

RxJava中可用的调度器种类:

调度器类型 | 效果
---|---
Schedulers.computation() | 用于计算任务，如事件循环或回调处理，不要用IO操作（IO操作请使用Scheduler.io()）; 默认线程数等于处理器的数量
Schedulers.from(executor) | 使用指定的Executor作为调度器
Schedulers.immediate() | 在当前线程立即开始执行任务
Schedulers.io() | 用于IO密集型任务，如异步阻塞IO操作；默认是一个CachedThreadScheduler，像一个有线程缓存的新线程调度器
Schedulers.newThread() | 为每一个任务创建一个新的线程
Schedulers.trampoline() | 当其它排队的任务完成后，在当前线程排队开始执行


RxAndroid中添加了专用的AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行，也就是我们的UI线程。

**使用SubScribeOn和ObserverOn操作符**

- SubscribeOn(): 订阅事件发生的线程，事件产生的线程，只允许执行一次
- ObserveOn：指定回调发生的线程，事件消费的线程，可以执行多次

```
.subscribeOn(Schedulers.io())
.observeOn(AndroidSchedulers.mainThread())
```

**基本用法：**
```
Observable.just("One", "Two", "Three")
    .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程,事件产生在Io线程
    .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
    .subscribe(new Action1<String>() {
    
        @Override
        public void call(String s) {
            Log.i("RxJavaTest",  s);
        }
    });
```

**多次切换线程**

```
Observable.just("One"，"Two ","Three") // 发生在IO 线程，由 subscribeOn() 指定，subscribeOn()只允许执行一次，你也可以指定多个，但是只有第一个起作用
    .subscribeOn(Schedulers.io())
    .observeOn(Schedulers.newThread())
    .map() // 发生在新线程，由 observeOn() 指定
    .observeOn(Schedulers.io())
    .map() // 发生在IO 线程，由 observeOn() 指定
    .observeOn(AndroidSchedulers.mainThread)
    .subscribe(subscriber);  // 发生在UI主线程，由 observeOn() 指定
```
