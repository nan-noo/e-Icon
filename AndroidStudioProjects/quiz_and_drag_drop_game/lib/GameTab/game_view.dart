import 'package:flutter/material.dart';
import './Component/recyclingP.dart';

class RecyclingP extends StatelessWidget {
  const RecyclingP({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("RecyclingP"),
          automaticallyImplyLeading: true,
          leading: IconButton(
            icon:Icon(Icons.arrow_back),
            onPressed: () { Navigator.pop(context, false); },
          ),
        ),
        body: Center(
          child: RecyclingPView(),
        )
    );
  }
}

class FoodWasteQuiz extends StatelessWidget {
  const FoodWasteQuiz({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Food Waste Quiz"),
          automaticallyImplyLeading: true,
          leading: IconButton(
            icon:Icon(Icons.arrow_back),
            onPressed: () { Navigator.pop(context, false); },
          ),
        ),
        body: Center(
          child: const Text("Hello!"),
        )
    );
  }
}

class PlasticQuiz extends StatelessWidget {
  const PlasticQuiz({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Plastic Quiz"),
          automaticallyImplyLeading: true,
          leading: IconButton(
            icon:Icon(Icons.arrow_back),
            onPressed: () { Navigator.pop(context, false); },
          ),
        ),
        body: Center(
          child: const Text("Hello!"),
        )
    );
  }
}
