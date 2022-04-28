import 'package:flutter/material.dart';

class Song {
  final String name;
  final String singer;
  final String image;
  final int duration;
  final Color color;
  Song(
      {required this.name,
      required this.singer,
      required this.image,
      required this.duration,
      required this.color});
}

List<Song> mostPopular = [
  Song(
      name: "푸르던 (曾经蔚蓝)",
      image: "assets/img/iu2.jpeg",
      singer: "IU",
      duration: 300,
      color: Color(0xFF9184CB)),
  Song(
      name: "七里香",
      image: "assets/img/qlx.jpeg",
      singer: "周杰伦",
      duration: 252,
      color: Color(0xFF416D42)),
  Song(
      name: "Shape Of You",
      image: "assets/img/soy.jpeg",
      singer: "Ed Sheeran",
      duration: 300,
      color: Color(0xFF11AEDD)),
  Song(
      name: "美好事物",
      image: "assets/img/fddm.jpeg",
      singer: "房东的猫",
      duration: 264,
      color: Color(0xFFC2CCD2))
];

List<Song> newRelease = [
  Song(
      name: "푸르던 (曾经蔚蓝)",
      image: "assets/img/iu2.jpeg",
      singer: "IU",
      duration: 300,
      color: Color(0xFF9184CB)),
  Song(
      name: "七里香",
      image: "assets/img/qlx.jpeg",
      singer: "周杰伦",
      duration: 252,
      color: Color(0xFF416D42)),
  Song(
      name: "Shape Of You",
      image: "assets/img/soy.jpeg",
      singer: "Ed Sheeran",
      duration: 300,
      color: Color(0xFF11AEDD)),
  Song(
      name: "美好事物",
      image: "assets/img/fddm.jpeg",
      singer: "房东的猫",
      duration: 264,
      color: Color(0xFFC2CCD2))
];
