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
          ConverterApp(),
          TwoWayConverterApp(),
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

String convertToCelsius(String fahrenheit) {
  final dFahrenheit = double.tryParse(fahrenheit) ?? 0;
  final dCelsius = (dFahrenheit - 32) * 5 / 9;
  return dCelsius.toString();
}

class StatefulTemperatureInput extends StatefulWidget {
  const StatefulTemperatureInput({super.key});

  @override
  State<StatefulTemperatureInput> createState() =>
      _StatefulTemperatureInputState();
}

class _StatefulTemperatureInputState extends State<StatefulTemperatureInput> {
  var output = "";

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
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: enterCelsius,
              border: OutlineInputBorder(),
            ),
            onChanged: (newInput) {
              setState(() {
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

class ConverterApp extends StatefulWidget {
  const ConverterApp({super.key});

  @override
  State<ConverterApp> createState() => _ConverterAppState();
}

class _ConverterAppState extends State<ConverterApp> {
  var output = "";

  @override
  Widget build(BuildContext context) {
    return StatelessTemperatureInput(
      output: output,
      onValueChange: (newInput) {
        setState(() {
          output = convertToFahrenheit(newInput);
        });
      },
    );
  }
}

class StatelessTemperatureInput extends StatelessWidget {
  final String output;
  final Function(String newInput) onValueChange;

  const StatelessTemperatureInput({
    super.key,
    required this.output,
    required this.onValueChange,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            statelessConverter,
            style: Theme.of(context).textTheme.headlineSmall,
          ),
          TextFormField(
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: enterCelsius,
              border: OutlineInputBorder(),
            ),
            onChanged: (value) => onValueChange(value),
          ),
          Text(temperatureFahrenheit(output))
        ],
      ),
    );
  }
}

enum Scale {
  celsius("Celcius"),
  fahrenheit("Fahrenheit");

  final String scaleName;
  const Scale(this.scaleName);
}

class GeneralTemperaturInput extends StatelessWidget {
  final TextEditingController controller;
  final Scale scale;
  final Function(String newInput) onValueChange;

  const GeneralTemperaturInput({
    super.key,
    required this.controller,
    required this.scale,
    required this.onValueChange,
  });

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 4),
      child: TextFormField(
        controller: controller,
        keyboardType: TextInputType.number,
        decoration: InputDecoration(
          hintText: enterTemperature(scale.scaleName),
          border: const OutlineInputBorder(),
        ),
        onChanged: (value) => onValueChange(value),
      ),
    );
  }
}

class TwoWayConverterApp extends StatefulWidget {
  const TwoWayConverterApp({super.key});

  @override
  State<TwoWayConverterApp> createState() => _TwoWayConverterAppState();
}

class _TwoWayConverterAppState extends State<TwoWayConverterApp> {
  String celsius = "";
  String fahrenheit = "";
  final TextEditingController cCelcius = TextEditingController();
  final TextEditingController cFahrenheit = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            twoWayConverter,
            style: Theme.of(context).textTheme.headlineSmall,
          ),
          GeneralTemperaturInput(
            controller: cCelcius,
            scale: Scale.celsius,
            onValueChange: (newInput) {
              fahrenheit = convertToFahrenheit(newInput);
              cFahrenheit.text = fahrenheit;
            },
          ),
          GeneralTemperaturInput(
            controller: cFahrenheit,
            scale: Scale.fahrenheit,
            onValueChange: (newInput) {
              celsius = convertToCelsius(newInput);
              cCelcius.text = celsius;
            },
          ),
        ],
      ),
    );
  }
}
