import 'dart:io';
import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:musicplayer/pages/mainPages/DiscoverPage.dart';
import 'package:musicplayer/pages/mainPages/MinePage.dart';
import 'package:musicplayer/pages/mainPages/MyFavoritePage.dart';
import 'package:musicplayer/res/other/database.dart';
import 'package:musicplayer/res/svg/svg.dart';


import 'mainPages/HomePage.dart';
import 'mainPages/musicPlayer.dart';

class MainPage extends StatefulWidget{
   MainPage({Key? key}) : super(key: key);
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage>{
  final int _curIndex = 0;
  List<Widget> mPageList = [
    HomePage(),
    DiscoverPage(),
    MyFavoritePage(),
    MinePage()
  ];
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: CupertinoNavigationBar(
          backgroundColor: const Color(0xFFE3EAFC),
          // backgroundColor: Colors.transparent,
          leading:IconButton(
              onPressed: (){
                Navigator.pushNamed(context, "/searchPage");
              }, icon: Icon(CupertinoIcons.search),color: Color(0xFF575A5E),),
          trailing: Builder(
            builder: (context){
              return IconButton(onPressed: (){
                // 侧栏打开抽屉
                Scaffold.of(context).openEndDrawer();
                print("hello");
              }, icon: sideSvgIcon);
            },
          ),
        ),
        body: DockTabs(),
        endDrawer: EndDrawer(),
        floatingActionButton: PlayerHome(currentSong),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
        // bottomNavigationBar:
        //     Container(
        //       decoration: BoxDecoration(
        //         boxShadow: [ BoxShadow(
        //           blurRadius: 14,
        //           spreadRadius: 16,
        //           color: Colors.black.withOpacity(0.2),
        //         )],
        //       ),
        //       child:ClipRRect(
        //         child: BackdropFilter(
        //           filter: ImageFilter.blur(sigmaX: 6, sigmaY: 6),
        //           child:Container(
        //             decoration: BoxDecoration(
        //               color: Colors.white.withOpacity(0.7),
        //             ),
        //             child: Theme(data: Theme.of(context).copyWith(canvasColor: Colors.white.withOpacity(0.1)),
        //               child: BottomNavigationBar(
        //                 backgroundColor: Colors.transparent,
        //                 currentIndex: _curIndex,
        //                 onTap: (index){
        //                   setState(() {
        //                     _curIndex = index;
        //                   });
        //                 },
        //                 items: [
        //                   BottomNavigationBarItem(
        //                       icon: _curIndex == 0?homeSvgIconSelected:homeSvgIcon,
        //                       label: "主页"),
        //                   BottomNavigationBarItem(
        //                       icon: _curIndex == 1?discoverSvgIconSelected:discoverSvgIcon,
        //                       label: "发现"),
        //                   BottomNavigationBarItem(
        //                       icon: _curIndex == 2?heartSvgIconSelected:heartSvgIcon,
        //                       label: "喜欢"),
        //                   BottomNavigationBarItem(
        //                       icon: _curIndex == 3?mineSvgIconSelected:mineSvgIcon,
        //                       label: "我的"),
        //                 ],
        //               ),
        //
        //             ),
        //
        //           )
        //
        //         ),
        //       ),
        //     )

      );
  }
}





/// Dock Tabs
class DockTabs extends StatefulWidget{
   DockTabs({Key? key}) : super(key: key);
  _DockTabsState createState() => _DockTabsState();
}

class _DockTabsState extends State<DockTabs>{
  int curIndex = 0;
  List<Widget> mPageList = [];
  @override
  void initState() {
    super.initState();
    mPageList.add(HomePage());
    mPageList.add(DiscoverPage());
    mPageList.add(MyFavoritePage());
    mPageList.add(MinePage());
  }
  @override
  Widget build(BuildContext context) {
    return CupertinoTabScaffold(
      tabBar: CupertinoTabBar(
        currentIndex: curIndex,
        onTap: (index) => {
          setState(() {
            curIndex = index;
          }),
          // print(index)
        },
        
        items: [
          BottomNavigationBarItem(
              icon: curIndex == 0?homeSvgIconSelected:homeSvgIcon,
              label: "主页"),
          BottomNavigationBarItem(
              icon: curIndex == 1?discoverSvgIconSelected:discoverSvgIcon,
              label: "发现"),
          BottomNavigationBarItem(
              icon: curIndex == 2?heartSvgIconSelected:heartSvgIcon,
              label: "喜欢"),
          BottomNavigationBarItem(
              icon: curIndex == 3?mineSvgIconSelected:mineSvgIcon,
              label: "我的"),
        ],
      ),
      tabBuilder: (BuildContext context, int index) {
        return PageView.builder(
          onPageChanged: (index){
            setState(() {
              curIndex = index;
              // curIndex = index % mPageList.length;
            });
          },
          itemCount:4,
          itemBuilder: (BuildContext context, int index) {
              return mPageList[curIndex];
          },
        );
      },
    );
  }
}
/// 侧栏
class EndDrawer extends StatefulWidget{
   EndDrawer({Key? key}) : super(key: key);
  _EndDrawerState createState() => _EndDrawerState();
}

class _EndDrawerState extends State<EndDrawer>{
  @override
  Widget build(BuildContext context) {
    final double screenHeight = MediaQuery.of(context).size.height;
    final double screenWidth = MediaQuery.of(context).size.width;
    return Container(
        width: screenWidth,
        child: Drawer(
          child: BackdropFilter(
            filter: ImageFilter.blur(sigmaX: 20.0,sigmaY: 20.0),
            child:Container(
              width: screenWidth,
              decoration:  BoxDecoration(
                // color: Color(0xDCE2EAFF).withOpacity(0.7),
                gradient: LinearGradient(
                  begin: Alignment.topLeft,
                  end: Alignment.bottomRight,
                  colors: [
                    Color(0xDCE2EAFF).withOpacity(0.7),
                    Color(0x0fdce2ea).withOpacity(0.7),
                  ],
                ),
              ),
              child: ListView(
                padding: EdgeInsets.zero,
                children: [
                  Column(
                    children: [
                      SizedBox(height: 60,),
                      Stack(
                        // alignment:Alignment.bottomCenter,
                        children: [
                            // 卡片
                            Container(
                            width: screenWidth,
                            height: 100,
                              // alignment: Alignment.center,
                            margin: const EdgeInsets.fromLTRB(30, 40, 30, 20),
                            child: Card(
                              // 圆角
                              shape: const RoundedRectangleBorder(
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),),
                              elevation: 1.0, // 阴影
                              // margin: EdgeInsets.fromLTRB(20, 0, 20, 20),
                              child:Column(
                                children: [
                                  SizedBox(height:55),
                                  InkWell(
                                    highlightColor:Color(0xE5EAFF),
                                    splashColor: Color(0xF4F8FF),
                                    borderRadius: BorderRadius.all(Radius.circular(20.0)),
                                    child: Text("Name",style: TextStyle(fontSize: 20),),
                                    onTap: (){
                                      print("点击用户名");
                                    },),
                                  SizedBox(height:10),
                                ],
                              ),
                            ),
                          ),
                          Container(
                            alignment: Alignment.center,
                            child: InkWell(
                              borderRadius: BorderRadius.circular(45),
                              child: const CircleAvatar(
                                maxRadius: 45,
                                backgroundImage: NetworkImage("https://sns-img-qc.xhscdn.com/b191f5d4-b786-3f34-9206-6231a9133a30"),
                              ),
                              onTap: (){
                                print("点击头像");
                              },),
                          ),


                        ],
                      ),
//                功能列表
                      Container(
                        width: 370,
                        child: Card(
                          // 圆角
                          shape: const RoundedRectangleBorder(
                            borderRadius: BorderRadius.all(Radius.circular(20.0)),),
                          elevation: 1.0, // 阴影
                          margin: EdgeInsets.fromLTRB(20, 0, 20, 0),
                          child:Column(
                            children: [
                              SizedBox(height: 10,),
//                        我的最爱
                              InkWell(
                                highlightColor:Color(0xDCE5EAFF),
                                splashColor: Color(0xA9F4F8FF),
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),
                                child: Row(
                                  children: [
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      width: 25,
                                      child: heartSvgIcon1,
                                    ),
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      height: 25,
                                      child: Text("我的最爱",style: TextStyle(fontSize: 18,color: CupertinoColors.black) ,),
                                    ),
                                  ],
                                ),
                                onTap: (){
                                  print("我的最爱");
                                },
                              ),
//                        历史播放
                              InkWell(
                                highlightColor:Color(0xDCE5EAFF),
                                splashColor: Color(0xA9F4F8FF),
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),
                                child: Row(
                                  children: [
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      width: 25,
                                      child: historyPlaySvgIcon,
                                    ),
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      height: 25,
                                      child: Text("历史播放",style: TextStyle(fontSize: 18,color: CupertinoColors.black) ,),
                                    ),
                                  ],
                                ),
                                onTap: (){
                                  print("历史播放");
                                },
                              ),
//                        定时关闭
                              InkWell(
                                highlightColor:Color(0xDCE5EAFF),
                                splashColor: Color(0xA9F4F8FF),
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),
                                child: Row(
                                  children: [
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      width: 25,
                                      child: timeSvgIcon,
                                    ),
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      height: 25,
                                      child: Text("定时关闭",style: TextStyle(fontSize: 18,color: CupertinoColors.black) ,),
                                    ),
                                  ],
                                ),
                                onTap: (){
                                  print("定时关闭");
                                },
                              ),
//                        关于
                              InkWell(
                                highlightColor:Color(0xDCE5EAFF),
                                splashColor: Color(0xA9F4F8FF),
                                borderRadius: BorderRadius.all(Radius.circular(20.0)),
                                child: Row(
                                  children: [
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      width: 25,
                                      child: aboutSvgIcon,
                                    ),
                                    Container(
                                      margin: EdgeInsets.fromLTRB(20, 10, 0, 10),
                                      height: 25,
                                      child: Text("关于",style: TextStyle(fontSize: 18,color: CupertinoColors.black)),
                                    ),
                                  ],
                                ),
                                onTap: (){
                                  print("关于");
                                },
                              ),
                              SizedBox(height: 10,),
                            ],
                          ),
                        ),
                      ),
                      SizedBox(height:20),
                      // 退出
                      Container(
                        width: 370,
                        child: Card(
                          // 圆角
                          shape: const RoundedRectangleBorder(
                            borderRadius: BorderRadius.all(Radius.circular(20.0)),),
                          elevation: 1.0, // 阴影
                          margin: EdgeInsets.fromLTRB(20, 0, 20, 0),
                          child:InkWell(
                            highlightColor:Color(0x48FF293A),
                            splashColor: Color(0x2FFFDCDC),
                            borderRadius: BorderRadius.all(Radius.circular(20.0)),
                            onTap: (){
                              print("tuichu");
                              exit(0);
                            },
                            child:Column(
                              children: const [
                                SizedBox(height: 8,),
                                Text("退 出",style: TextStyle(
                                    fontSize: 20,
                                    color: Color(
                                        0xFFFF5050),
                                    fontWeight: FontWeight.bold ),),
                                SizedBox(height: 8,),
                              ],
                            ),),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
        )
    );
  }
}

Song currentSong = Song(
    name: "푸르던 (曾经蔚蓝)",
    singer: "IU",
    image: "assets/img/iu2.jpeg",
    duration: 100,
    color: Color(0xFFE2E2E3));
double currentSlider = 0;

/// 底部mini播放器
class PlayerHome extends StatefulWidget {
  final Song song;
  PlayerHome(this.song);

  @override
  _PlayerHomeState createState() => _PlayerHomeState();
}
class _PlayerHomeState extends State<PlayerHome> {
  @override
  Widget build(BuildContext context) {
    final double screenWidth = MediaQuery.of(context).size.width;
    return GestureDetector(
      onTap: () {
        Navigator.push(
            context,
            PageRouteBuilder(
                pageBuilder: (context, _, __) => MusicPlayer(widget.song)));
      },
      /// mini 播放器框
      child:
      ///毛玻璃
      ClipRRect(
        borderRadius: BorderRadius.vertical(top: Radius.circular(20)),
        child: BackdropFilter(
          filter: ImageFilter.blur(sigmaX: 10.0,sigmaY: 10.0),
          child: Container(
            height: 77,
            width: screenWidth,
            padding: EdgeInsets.all(5),
            decoration: BoxDecoration(
              color: Color(0xfff5f5f7).withOpacity(0.9),
              borderRadius: BorderRadius.vertical(
                top: Radius.elliptical(20, 20),
              ),
            ),
            child:
            Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Row(
                      children: [
                        Container(
                          // tag: "image",
                          margin: EdgeInsets.fromLTRB(10, 0, 0, 0),
                          child:
                          Hero(// 动画
                            tag: "image",
                            child:  CircleAvatar(
                              backgroundImage: AssetImage(widget.song.image),
                              radius: 22,
                            ),
                          ),
                        ),
                        SizedBox(
                          width: 20,
                        ),
                        Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Text(widget.song.name,
                                style: TextStyle(
                                  color: Colors.black,
                                  fontSize: 12,
                                )),
                            Padding(
                              padding:EdgeInsets.fromLTRB(0, 4, 0, 0),
                              child: Text(" — ",style: TextStyle(
                                fontSize: 12,),),
                            ),
                            Padding(
                                padding:EdgeInsets.fromLTRB(0, 4, 0, 0),
                                child:  Text(widget.song.singer,
                                    style: TextStyle(
                                      color: Colors.grey,
                                      fontSize: 12,
                                    ))
                            ),
                          ],
                        ),
                      ],
                    ),
                    Row(
                      children: [
                        IconButton(
                          onPressed: (){
                          },
                          icon: playSvgIconBlack,
                          color: Colors.black,
                          // child:,
                        ),
                        SizedBox(
                          width: 10,
                        ),
                        IconButton(
                          onPressed: (){},
                          icon: playListSvgIconBlack,),
                        SizedBox(
                          width: 10,
                        ),
                      ],
                    )
                  ],
                ),
              ],
            ),
          ),
        ),
      ),

    );
  }
}

