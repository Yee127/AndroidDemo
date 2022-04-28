import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:musicplayer/res/other/database.dart';
import 'package:musicplayer/res/svg/svg.dart';


class MusicPlayer extends StatefulWidget {
  final Song song;
  MusicPlayer(this.song);
  @override
  _MusicPlayerState createState() => _MusicPlayerState();
}
double currentSlider = 0;
class _MusicPlayerState extends State<MusicPlayer> {
  @override
  Widget build(BuildContext context) {
    final double screenHeight = MediaQuery.of(context).size.height;
    final double screenWidth = MediaQuery.of(context).size.width;
    return Hero(
        tag: "image",
        child: Stack(
          children: [
            Container(
                height: screenHeight,
                width: screenWidth,
                decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.5),
                    image: DecorationImage(
                        image: AssetImage(widget.song.image), fit: BoxFit.cover)),
              ),
            Container(
              height: screenHeight,
              width: screenWidth,
              decoration: BoxDecoration(
                boxShadow:[ BoxShadow(
                  blurRadius: 14,
                  spreadRadius: 16,
                  color: Colors.black.withOpacity(0.3),
                )],
              ),
              child:
              ClipRRect(
                child: BackdropFilter(
                  filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10),
                  child: Container(
                    decoration: BoxDecoration(
                      color: Colors.white.withOpacity(0.2)
                    ),
                  ),
                ),),
            ),
        Scaffold(
          backgroundColor: Colors.transparent,
          appBar: AppBar(
            leading:InkWell(
              splashColor:Colors.transparent,
              highlightColor:Colors.transparent,
              child: Icon(Icons.keyboard_arrow_down),
              onTap: (){
                Navigator.pop(context);
              },
            ),
            backgroundColor: Colors.transparent,
            elevation: 0,
            centerTitle: true,
            title: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Text(
                  widget.song.name,
                  style: TextStyle(fontWeight: FontWeight.bold,fontSize: 18),
                ),
                Text(widget.song.singer, style: TextStyle(fontSize: 10))
              ],
            ),
            actions: [
              Padding(
                padding: EdgeInsets.only(right: 15, left: 15),
                child: likeSvgIcon,
              )
            ],
          ),
          body:Stack(
            children: [
              AlbumWidget(widget.song.image),
              ShowLyric(),
              DockPlayer(widget.song)
            ],
          ),
        ),
      ],
    ));
  }
}
/// 歌词
class ShowLyric extends StatefulWidget{
   ShowLyric({Key? key}) : super(key: key);
  _ShowLyricState createState() => _ShowLyricState();
}

class _ShowLyricState extends State<ShowLyric>{

  @override
  Widget build(BuildContext context) {
    final double screenHeight = MediaQuery.of(context).size.height;
    final double screenWidth = MediaQuery.of(context).size.width;
    return Positioned(
      top: screenHeight/2.1,
      child: Container(
      width: MediaQuery.of(context).size.width,
      child: Column(
        children:  [
          Padding(
            padding: EdgeInsets.fromLTRB(0, 0, 0, 5),
            child: Text("까만 하늘 귀뚜라미 울음소리",style: TextStyle(
                fontSize: 15,
                color: Colors.white54
            ),),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(0, 0, 0, 5),
            child: Text("힘을 주어 잡고 있던 작은 손",style: TextStyle(
                fontSize: 20,
                color: Colors.white
            ),),
          ),
          Padding(
            padding: EdgeInsets.fromLTRB(0, 0, 0, 5),
            child: Text("너는 조용히 내려 나의 가물은 곳에 고이고",style: TextStyle(
                fontSize: 15,
                color: Colors.white54
            ),),
          ),
        ],
      ),
    ),);

  }
}
/// 专辑图片
class AlbumWidget extends StatelessWidget{
  late String image;
  AlbumWidget(this.image);

  @override
  Widget build(BuildContext context) {
    final double screenHeight = MediaQuery.of(context).size.height;
    final double screenWidth = MediaQuery.of(context).size.width;
    return Container(
        height: screenHeight/2,
        child: Align(
          alignment: Alignment.center,
          child: Container( // 毛玻璃圆
              height: screenWidth*0.5 + 50,
              width: screenWidth*0.5 + 50,
              decoration: BoxDecoration(
                color: Colors.black.withOpacity(0.2),
                borderRadius: BorderRadius.circular(screenHeight/2),
                border: Border.all(
                  width: 5,
                  color: Colors.white.withOpacity(0.2),
                )
              ),
              child:
              ClipRRect(
              borderRadius: BorderRadius.circular(screenHeight/2),
              child: BackdropFilter(
              filter: ImageFilter.blur(sigmaX: 6, sigmaY: 6),
              child:Container(
                // height: screenWidth*0.5 ,
                // width: screenWidth*0.5 ,
                decoration: BoxDecoration(
                color: Colors.white.withOpacity(0.2),
                borderRadius: BorderRadius.circular(screenHeight/2),
                border: Border.all(
                  width: 5, color: Colors.white.withOpacity(0.2))),
                child:CircleAvatar(
                  backgroundImage: AssetImage(image),
                  // radius: 200,
                ),),)
            ),
        ),
      )
    );
  }
}
/// 底部播放器
class DockPlayer extends StatefulWidget{
  final Song song;
   DockPlayer(this.song,{Key? key}) : super(key: key);
  _DockPlayerState createState() => _DockPlayerState();
}

class _DockPlayerState extends State<DockPlayer>{
  
  @override
  Widget build(BuildContext context) {
    return 
      Align(
        alignment: Alignment.bottomCenter,
        child:
        Container(
          decoration: BoxDecoration(boxShadow: [
            BoxShadow(
              blurRadius: 14,
              spreadRadius: 16,
              color: Colors.black.withOpacity(0.25),
            )
          ]),
          child: ClipRRect(
            borderRadius: BorderRadius.circular(20),
            child: BackdropFilter(
              filter: ImageFilter.blur(sigmaX: 6, sigmaY: 6),
              child: Container(
                height: 200,
                width: MediaQuery.of(context).size.width,
                decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.2),
                    borderRadius: BorderRadius.circular(20),
                    border: Border.all(
                        width: 1.5, color: Colors.white.withOpacity(0.2))),
                child:
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SizedBox(height: 30,),
                    Container(
                      width: MediaQuery.of(context).size.width,
                      child:
                      SliderTheme(
                        data: SliderTheme.of(context).copyWith(
                            thumbShape: RoundSliderThumbShape(
                              pressedElevation: 10,
                                elevation:1,
                                enabledThumbRadius: 3,
                              disabledThumbRadius: 2
                            ),
                            trackHeight: 5),
                        child:
                        Slider(
                          value: currentSlider,
                          max: widget.song.duration.toDouble(),
                          min: 0,
                          inactiveColor: Colors.white70,
                          activeColor: Color(0xFFB8AFE3),
                          onChanged: (val) {
                            currentSlider = val;
                            setState(() {});
                          },
                        ),
                      ),
                    ),
                    Padding(
                      padding: EdgeInsets.symmetric(horizontal: 20),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text(
                            Duration(seconds: currentSlider.toInt())
                                .toString()
                                .split('.')[0]
                                .substring(2),
                            style: TextStyle(color: Colors.white),
                          ),
                          Text(
                            Duration(seconds: widget.song.duration.toInt())
                                .toString()
                                .split('.')[0]
                                .substring(2),
                            style: TextStyle(color: Colors.white),
                          )
                        ],
                      ),
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: [
                        IconButton(
                            onPressed: (){
                            print("随机播放");
                          },
                            icon: randomSvgIcon,
                        ),
                        IconButton(
                            onPressed: (){
                            print("前一首");
                          },
                            icon: preSvgIcon,
                            iconSize: 10,
                        ),
                        IconButton(
                            onPressed: (){
                            print("开始播放");
                          },
                            icon: playSvgIcon,
                            iconSize: 70,),
                        IconButton(onPressed: (){
                          print("下一首");
                        },
                            icon: nextSvgIcon,
                            iconSize: 10,
                        ),
                        IconButton(
                            onPressed: (){
                            print("歌单列表");
                          },
                            icon: playListSvgIcon),
                      ],

                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      );
    throw UnimplementedError();
  }
}


