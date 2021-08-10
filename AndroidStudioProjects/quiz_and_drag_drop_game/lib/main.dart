import 'package:flutter/material.dart';
import './CartoonTab/cartoon_tab.dart';
import './GameTab/game_tab.dart';
import './UserTab/user_tab.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MainPage(),
    );
  }
}

class MainPage extends StatefulWidget {
  const MainPage({Key? key}) : super(key: key);

  @override
  _MainPageState createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Scaffold(
        bottomNavigationBar: menu(),
        body: TabBarView(
          children: <Widget>[
            CartoonTab(),
            GameTab(),
            UserTab()
          ],
        ),
      ),
    );
  }
}

Widget menu() {
  return Container(
    color: Colors.blue,
      child: TabBar(
        labelColor: Colors.white,
        unselectedLabelColor: Colors.white70,
        indicatorSize: TabBarIndicatorSize.tab,
        indicatorPadding: EdgeInsets.all(5.0),
        indicatorColor: Colors.indigo,
        tabs: [
          Tab(text: "Cartoon",
              icon: Icon(Icons.menu_book)
          ),
          Tab(text: "Game",
              icon: Icon(Icons.videogame_asset)
          ),
          Tab(text: "User",
              icon: Icon(Icons.person)
          ),
        ],
      ),
  );
}

