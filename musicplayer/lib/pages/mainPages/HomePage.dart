import 'dart:ui';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:musicplayer/pages/mainPages/musicPlayer.dart';
import 'package:musicplayer/res/other/database.dart';

class HomePage extends StatefulWidget{
  HomePage({Key? key}) : super(key: key);
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage>{
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        gradient: LinearGradient(
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
          colors: [
            Color(0xDCE2EAFF),
            Color(0x0fdce2ea),
          ],
        ),
      ),
      child: Scaffold(
        backgroundColor: Colors.transparent,
        body: Recommend(),
      ),
    );

  }
}

class RecommendWidget extends StatefulWidget{
   RecommendWidget({Key? key}) : super(key: key);
  _RecommendWidgetState createState() => _RecommendWidgetState();
}

class _RecommendWidgetState extends State<RecommendWidget>{

  @override
  Widget build(BuildContext context) {
    final double screenHeight = MediaQuery.of(context).size.height;
    return SingleChildScrollView(
      child: Container(
        height: screenHeight,
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Padding(
              padding: EdgeInsets.only(left: 20,top: 20,bottom: 10),
              child: Text(
                "今日推荐",
                style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.bold,
                    color: Colors.black),
              ),
            ),
            Container(
              height: 300,
              child: TrackWidget(refresh),
            ),
            Padding(
              padding: EdgeInsets.only(left: 20,top: 20,bottom: 10),
              child: Text(
                "For You",
                style: TextStyle(
                    fontSize: 32,
                    fontWeight: FontWeight.bold,
                    color: Colors.black),
              ),
            ),
            ForYouWidget(),
            /// 圆形歌单区
            CircleTrackWidget(
              song: newRelease,
              title: "最新歌单",
              // subtitle: "3456 songs",
              notifyParent: refresh,
            ),
            RecommendSingerWidget(
              song: mostPopular,
              title: "推荐艺人",
              // subtitle: "346 songs",
              notifyParent: refresh,
            ),
            SizedBox(  // 底部留空
              height: 130,
            )
          ],
        ),
      ),
    );

  }
}

Widget Recommend(){
  return SingleChildScrollView(
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: EdgeInsets.only(left: 20,top: 20,bottom: 10),
          child: Text(
            "推荐歌单",
            style: TextStyle(
                fontSize: 32,
                fontWeight: FontWeight.bold,
                color: Colors.black),
          ),
        ),
        Container(
          height: 300,
          child: TrackWidget(refresh),
        ),
        Padding(
          padding: EdgeInsets.only(left: 20,top: 20,bottom: 10),
          child: Text(
            "For You",
            style: TextStyle(
                fontSize: 32,
                fontWeight: FontWeight.bold,
                color: Colors.black),
          ),
        ),
        ForYouWidget(),
        /// 圆形歌单区
        CircleTrackWidget(
          song: newRelease,
          title: "最新歌单",
          // subtitle: "3456 songs",
          notifyParent: refresh,
        ),
        RecommendSingerWidget(
          song: mostPopular,
          title: "推荐艺人",
          // subtitle: "346 songs",
          notifyParent: refresh,
        ),
        SizedBox(  // 底部留空
          height: 50,
        )
      ],
    ),
  );
}

refresh() {
  // setState(() {});
}
Song currentSong = Song(
    name: "title",
    singer: "singer",
    image: "assets/img/song1.jpg",
    duration: 100,
    color: Color(0xFFE2E2E3));
double currentSlider = 0;
/// 专辑图片
class TrackWidget extends StatelessWidget {
  final Function() notifyParent;
  TrackWidget(this.notifyParent);

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: mostPopular.length,
      shrinkWrap: true,
      scrollDirection: Axis.horizontal,
      itemBuilder: (BuildContext context, int index) {
        return GestureDetector(
          onTap: () {
            currentSong = mostPopular[index];
            currentSlider = 0;
            notifyParent();
          },
          child: Container(
            margin: EdgeInsets.only(left: 20),
            width: 200,
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                boxShadow: [
                  BoxShadow(
                      color: mostPopular[index].color,
                      blurRadius: 1,
                      spreadRadius: 0.3)
                ],
                image: DecorationImage(
                    image: AssetImage(mostPopular[index].image),
                    fit: BoxFit.cover)),
            child: Padding(
              padding: EdgeInsets.all(8),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.end,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    mostPopular[index].name,
                    style: TextStyle(
                      color: Colors.white70,
                      fontSize: 20,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Text(mostPopular[index].singer,
                      style: TextStyle(
                          color: Colors.white70,
                          fontWeight: FontWeight.bold,
                          fontSize: 12)),
                  SizedBox(
                    height: 20,
                  )
                ],
              ),
            ),
          ),
        );
      },
    );
  }
}
/// 最新歌单
class CircleTrackWidget extends StatelessWidget {
  final String title;
  final List<Song> song;
  // final String subtitle;
  final Function() notifyParent;

  CircleTrackWidget(
      {required this.title,
        required this.song,
        // required this.subtitle,
        required this.notifyParent});

  @override
  Widget build(BuildContext context) {
    return Container(  // 歌单列表区
      // height: 200,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: EdgeInsets.only(left: 20, top: 20,bottom: 10),
            child: Text(
              title,// 推荐标题
              style: TextStyle(
                  fontSize: 32,
                  fontWeight: FontWeight.bold,
                  color: Colors.black),
            ),
          ),
          Container(
            // 歌单列表区
            height: 145,
            child: ListView.builder(
              itemCount: song.length,
              scrollDirection: Axis.horizontal,
              shrinkWrap: true,
              itemBuilder: (BuildContext context, int index) {
                return GestureDetector(
                  onTap: () {
                    currentSong = song[index];
                    currentSlider = 0;
                    notifyParent();
                  },
                  child: Container(
                    margin: EdgeInsets.only(left: 20),
                    child: Column(
                      children: [
                        Container(
                          height: 120,
                          width: 120,
                          decoration: BoxDecoration(
                            image: DecorationImage(
                              image: AssetImage(song[index].image),
                              fit: BoxFit.cover
                            ),
                            borderRadius: BorderRadius.circular(20)
                          ),
                        ),
                        // CircleAvatar(
                        //   backgroundImage: AssetImage(song[index].image),
                        //   radius: 40,
                        // ),
                        SizedBox(
                          height: 5,
                        ),
                        Text(
                          song[index].name,
                          style: TextStyle(color: Colors.black),
                        ),
                        // Text(
                        //   song[index].singer,
                        //   style: TextStyle(color: Colors.grey),
                        // ),
                      ],
                    ),
                  ),
                );
              },
            ),
          )
        ],
      ),
    );
  }
}
/// For You
class ForYouWidget extends StatefulWidget{
   ForYouWidget({Key? key}) : super(key: key);
  _ForYouWidgetState createState() => _ForYouWidgetState();
}

class _ForYouWidgetState extends State<ForYouWidget>{
  @override
  Widget build(BuildContext context) {
    final double screenHeight = MediaQuery.of(context).size.height;
    final double screenWidth = MediaQuery.of(context).size.width;
    return Container(
      height: screenHeight*0.17,
      width: screenWidth,
      margin: EdgeInsets.fromLTRB(20, 0, 20, 0),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        image: DecorationImage(
          image: AssetImage("assets/img/recommend.jpg"),fit: BoxFit.cover,
        )
      ),
      child: Padding(
        padding: EdgeInsets.all((screenWidth/20)),
        child: Row(
          children: [
            Column(
              children: [
                Text("每 日",style: TextStyle(
                  fontSize: 35,
                  fontWeight: FontWeight.bold,
                  color: Colors.white
                ),),
                Text("推 荐",style: TextStyle(
                    fontSize: 35,
                    fontWeight: FontWeight.bold,
                    color: Colors.white
                ),)
              ],
            ),
            SizedBox(width: screenWidth*0.4,),
            IconButton(
                onPressed: (){
                  print("播放每日推荐");
                },
              icon: Icon(Icons.play_circle),
              iconSize: 50,
              color: Colors.white.withOpacity(0.8),
            )
          ],
        ),
      ),
    );
    throw UnimplementedError();
  }
}

/// 推荐艺人
class RecommendSingerWidget extends StatelessWidget {
  final String title;
  final List<Song> song;
  final Function() notifyParent;

  RecommendSingerWidget(
      {required this.title,
        required this.song,
        required this.notifyParent});

  @override
  Widget build(BuildContext context) {
    return Container(  // 歌单列表区
      // height: 300,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Padding(
            padding: EdgeInsets.only(left: 20, top: 20,bottom: 10),
            child: Text(
              title,// 推荐标题
              style: TextStyle(
                  fontSize: 32,
                  fontWeight: FontWeight.bold,
                  color: Colors.black),
            ),
          ),
          Container(// 歌单列表区
            height: 150,
            child: ListView.builder(
              itemCount: song.length,
              scrollDirection: Axis.horizontal,
              shrinkWrap: true,
              itemBuilder: (BuildContext context, int index) {
                return GestureDetector(
                  onTap: () {
                    currentSong = song[index];
                    currentSlider = 0;
                    notifyParent();
                  },
                  child: Container(
                    margin: EdgeInsets.only(left: 20),
                    child: Container(
                // margin: EdgeInsets.fromLTRB(20, 0, 0, 0),
                child:Column(
                  children: [
                    CircleAvatar(
                      backgroundImage: AssetImage(song[index].image),
                      radius: 60,
                    ),
                    SizedBox(
                      height: 5,
                    ),
                    Text(
                      song[index].singer,
                      style: TextStyle(color: Colors.black,
                      fontSize: 16),
                    ),
                  ],
                ),)
                  ),
                );
              },
            ),
          )
        ],
      ),
    );
  }
}