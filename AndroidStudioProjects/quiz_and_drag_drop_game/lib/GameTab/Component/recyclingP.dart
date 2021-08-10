import 'package:flutter/material.dart';

class RecyclingPView extends StatefulWidget {
  const RecyclingPView({Key? key}) : super(key: key);

  @override
  _RecyclingPViewState createState() => _RecyclingPViewState();
}

class _RecyclingPViewState extends State<RecyclingPView> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: [
          Expanded(
            child: Align(
              alignment: Alignment.center,
              child: _dragTarget(),
            ),
          ), // drop view
          Align(
            alignment: Alignment.bottomCenter,
            child: _itemBar(), // drag view
          )
        ],
      ),
    );
  }

  Widget _itemBar(){
    return Container(
      height: 100.0,
      child: ListView(
        scrollDirection: Axis.horizontal,
        children: <Widget>[
          _item(text: '1'),
          _item(text: '2'),
          _item(text: '3'),
          _item(text: '4'),
          _item(text: '5'),
        ],
      ),
    );
  }

  Widget _item({required String text,}){
    return LongPressDraggable(
        data: text,
        feedback: Text(text),
        child:  Container(
          margin: const EdgeInsets.only(left: 20.0),
          child: OutlinedButton(
            onPressed: () {
            },
            child: Text(text),
          ),
        ),
    );
  }

  Widget _dragTarget(){
    return DragTarget(
        builder: (BuildContext context, List<Object?> candidateData, List<dynamic> rejectedData) {
          return Expanded(
              child: Container(
                color: Colors.green,
                child: Center(child: Text("here!!!!!"),),
              )
          );
        },
        onWillAccept: (data){
          return true;
        },
        onAccept: (data){
          void _showToast(BuildContext context) {
            final scaffold = ScaffoldMessenger.of(context);
            scaffold.showSnackBar(
              SnackBar(
                content: Text(data.toString()),
              ),
            );
          }
          _showToast(context);
        },
    );
  }
}

