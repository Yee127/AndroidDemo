import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:musicplayer/routes/routes.dart';

void main() {
  runApp(const MyApp());
  // 沉浸式状态栏
  SystemUiOverlayStyle style = const SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      ///这是设置状态栏的图标和字体的颜色
      ///Brightness.light  一般都是显示为白色
      ///Brightness.dark 一般都是显示为黑色
      statusBarIconBrightness: Brightness.light
  );
  SystemChrome.setSystemUIOverlayStyle(style);
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        debugShowCheckedModeBanner: false,
        initialRoute: '/',     //初始化的时候加载的路由
        onGenerateRoute: onGenerateRoute);
  }
}