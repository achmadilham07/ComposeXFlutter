import 'package:degree_converter/utils/string.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: const <Widget>[
          StatefulTemperatureInput(),
        ],
      ),
    );
  }
}

String convertToFahrenheit(String celsius) {
  final dCelsius = double.tryParse(celsius) ?? 0;
  final dFahrenheit = (dCelsius * 9 / 5) + 32;
  return dFahrenheit.toString();
}

class StatefulTemperatureInput extends StatefulWidget {
  const StatefulTemperatureInput({super.key});

  @override
  State<StatefulTemperatureInput> createState() =>
      _StatefulTemperatureInputState();
}

class _StatefulTemperatureInputState extends State<StatefulTemperatureInput> {
  var input = "";
  var output = "";
  TextEditingController controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            statefulConverter,
            style: Theme.of(context).textTheme.headlineSmall,
          ),
          TextFormField(
            controller: controller,
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: enterCelsius,
              border: OutlineInputBorder(),
            ),
            onChanged: (newInput) {
              setState(() {
                input = newInput;
                output = convertToFahrenheit(newInput);
                
              });
            },
          ),
          Text(temperatureFahrenheit(output))
        ],
      ),
    );
  }
}
