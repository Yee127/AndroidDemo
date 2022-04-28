import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_search_bars/flutter_search_bars.dart';
import 'SearchBar.dart';

class SearchPage extends StatefulWidget{
   SearchPage({Key? key}) : super(key: key);
  _SearchPageState createState() => _SearchPageState();
}

class _SearchPageState extends State<SearchPage>{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CupertinoNavigationBar(
        backgroundColor: CupertinoColors.white.withOpacity(0.7),
        middle: ///这里只是用于显示的搜索框不用做输入
        ///参数[heroTag]用于页面过渡动画tag
        ///参数clickCallBack为当前搜索框点击事件回调
        SearchStaticBar(
          heroTag: "searchStatidBar",
          clickCallBack: () {
            Navigator.pop(context);
          },
        )
        ),
    );
    throw UnimplementedError();
  }
}