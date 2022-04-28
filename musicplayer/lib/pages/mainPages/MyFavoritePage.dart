import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class MyFavoritePage extends StatefulWidget{
   MyFavoritePage({Key? key}) : super(key: key);
  _MyFavoritePageState createState() => _MyFavoritePageState();
}

class _MyFavoritePageState extends State<MyFavoritePage>{
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        // image: DecorationImage(
        //   image: NetworkImage("https://img.zcool.cn/community/0372d195ac1cd55a8012062e3b16810.jpg"),fit: BoxFit.cover,
        // ),
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
        body: Body(),
      ),
    );

  }
}

class Body extends StatefulWidget{
  Body({Key? key}) : super(key: key);
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body>{
  @override
  Widget build(BuildContext context) {
    return Center(child: Text("MyFavoritePage"),);
    throw UnimplementedError();
  }
}
