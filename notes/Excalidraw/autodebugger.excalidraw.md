---

excalidraw-plugin: parsed
tags: [excalidraw]

---
==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠==

# Text Elements
DiSL ^pQCjRlv4

Instrumentation
(DiSLClass) ^EelKQlPD

Static Context ^GsmLIOyx

Guards ^qT6eFwCy

Markers ^BW9u1umz

Target App
(JAR) ^txccHeZH

GENERATED ^GbFqGJUF

Generated? ^UiBpapfC

Generated? ^j1HLiNPj

Generated? ^1AMdz9eT

Argument
for launching
DiSL ^7E198lp2

Exit Code ^lDyVJhmI

Instrumentation
side effects
(prints to stdout, 
stderr, writing to 
sockets, etc.) ^tQnFL1jm

DiSL Workflow ^aKKCwRSE

Target App 
(JAR) ^OPGFvG6O

Target Method Identifier ^Pu6HwSn1

Level of Instrumentation ^ifWqRkKt

Input ^7kbhC9LT

Auto-Debugger Workflow ^6VirS6WP

runner ^lJlw8FfD

ArgumentParser ^loMUxgbH

instrumentor-common ^2gIqyWJC

instrumentor-java ^VAqSuA1e

instrumentor-* ^kDZoHnVy

... ^b0d3ICuo

... ^k4TA8wPX

... ^eFCz8dFn

analyzer-disl ^VYFk6CQn

DiSLClass ^fFknYP3k

Static Context ^yohYlRWd

Guards ^Xhdf0pjf

Markers ^npQt6mzt

ResultCollector ^OXToPDiu

test-generator-common ^PVO4iRyP

test-generator-java ^uJk0tCAs

test-generator-* ^ojYBFmjG

... ^uU5A5SWe

... ^2SwRLGcR

... ^LYz2ITZ2

test-runner-common ^xKsZiXjW

test-runner-java ^xYXlpPkJ

... ^qeuCRoTj

... ^eXZmnJCd

... ^TnAK3ERV

test-runner-* ^n34vHhPc

UI ^6uho3E3P

TraceVisualizer ^ChALvbm1

Test Visualizer ^heqZelud

InstGenerator ^wsaP9FBK

InstCompiler ^SheNTpb8

DiSL Instrumentation Generating Workflow ^hn57Ldc9

Compiler ^8LyJdr4d

Packager ^hdcgYFfe

DiSL Class Layout ^Mi0RxdSs

package %s;

%s

public class %s {

  @After(marker = %s, scope = "%s")
  public static void %s(DynamicContext di) {
      int a = di.getLocalVariableValue(0, int.class);
      int b = di.getLocalVariableValue(1, int.class);
      System.out.println("disl: a=" + a);
      System.out.println("disl: b=" + b);
  }
} ^4i6mcveI

Create a DiSL Metamodel and use it to enforce structure of the DiSLClass?
    - The code can be generated using a M2T transformation
    - The M2T interface can be shared in the instrumentation-common module
    - The M2T logic for emitting Java could be injected to the common interface ^nPFHXBX1

Create a Test Metamodel and use it for generating tests?
    - Some sort of M2T could be reused from the instrumentation ^IZAaMQf2

package ^tuxnNtqC

PACKAGE_NAME ^sqOYTWB9

Imports ^m69vfT37

public class ^cWmgZsIA

CLASS_NAME ^tn3m2oJg

public static void  ^lj3DWIAe

INST_METHOD ^88Y1SQ7C

ANNOTATION ^VJ9X4kRW

MARKER ^DWOwZ7KK

SCOPE ^RqqC5xmK

DiSLClass Metamodel ^vue9riTH

DiSLClass ^LpLuHmZY

Package
----------------
- name ^z8WMWKAP

Import ^vtJuV5nU

Instrumentation Method
----------------
- name ^BVcrhTQT

Annotation
----------------
- activationTime ^kdRVEOYg

Marker
----------------
- markerClass ^nnWfe7qt

Scope
----------------
- methodIdentifier ^j8kacGJQ

Exportable
----------------
- varIdentifier ^QjWwbdgk

annotation ^c8RGK1OC

marker ^CIMNlXFP

targetMethod ^62VbvC6H

exports ^Q3nC5vOK

* ^x4y9zd4k

* ^DyOg4Lqf

logic ^ghpVqDv7

Metaclass
----------------
+ visit() ^Vp3oErAP

Model
----------------
- dislClass
+ visit() ^xyOy3KHx

* ^VeWtKuD9

package ^xrkK4CWm

imports ^0gbicnUv

Argument ^20lG8NBQ

Field ^syBjkXHE

Variable ^exwRPmNo

Code Generator ^cNY4tPV3

Model ^SbkwDMqU

Model::transform ^Edj2Ig7N

TextWriter ^APt98mUw

Source Path ^GmewwOPS

Object Path ^BYglDsra

JAR ^zFnuQkS3

INST_LOGIC ^mCPqZr05

ARGUMENTS ^lTiIcOPi

VARIABLES ^jl4SWRgt

FIELDS ^9kzMz2fJ

%%
# Drawing
```json
{
	"type": "excalidraw",
	"version": 2,
	"source": "https://github.com/zsviczian/obsidian-excalidraw-plugin/releases/tag/2.1.3",
	"elements": [
		{
			"type": "rectangle",
			"version": 134,
			"versionNonce": 1663234545,
			"isDeleted": false,
			"id": "kk64u9WEfepzCboSAfjuc",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 388.44921875,
			"y": 159.45703125,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 533.82421875,
			"height": 416.2578125,
			"seed": 1248694389,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "o5pmn0oMt3pUkyQ9pg-tK",
					"type": "arrow"
				},
				{
					"id": "raLbizLkNRg5Mt0V-AZ3p",
					"type": "arrow"
				},
				{
					"id": "mABUaLoE2kGn_5fJLAPjZ",
					"type": "arrow"
				}
			],
			"updated": 1714390729549,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 43,
			"versionNonce": 171057567,
			"isDeleted": false,
			"id": "pQCjRlv4",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 428,
			"y": 190,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 44.119964599609375,
			"height": 25,
			"seed": 366993115,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729549,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSL",
			"rawText": "DiSL",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "DiSL",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 108,
			"versionNonce": 45190097,
			"isDeleted": false,
			"id": "rLc-07RCyv7ZSkaOJjFKC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 402.5,
			"y": 228.37890625,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 198.94140625,
			"height": 337.05078125,
			"seed": 580795,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "EOEUN0JlL68WSd1zIOdgm",
					"type": "arrow"
				},
				{
					"id": "X18S--uqTnEt_dXaKXJ7r",
					"type": "arrow"
				},
				{
					"id": "CLIXwHBLEwke24wT5nqRF",
					"type": "arrow"
				}
			],
			"updated": 1714390729549,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 78,
			"versionNonce": 1675269567,
			"isDeleted": false,
			"id": "EelKQlPD",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 433,
			"y": 255,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 156.03985595703125,
			"height": 50,
			"seed": 1676532379,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Instrumentation\n(DiSLClass)",
			"rawText": "Instrumentation\n(DiSLClass)",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Instrumentation\n(DiSLClass)",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 120,
			"versionNonce": 1293019569,
			"isDeleted": false,
			"id": "nBMueA1qqfMuySkoII-ta",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 691.78125,
			"y": 232.40625,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 220,
			"height": 114.01953125,
			"seed": 1354551963,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "EOEUN0JlL68WSd1zIOdgm",
					"type": "arrow"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 57,
			"versionNonce": 882869727,
			"isDeleted": false,
			"id": "GsmLIOyx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 711,
			"y": 253,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 150.59986877441406,
			"height": 25,
			"seed": 1813443675,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Static Context",
			"rawText": "Static Context",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Static Context",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 116,
			"versionNonce": 191597457,
			"isDeleted": false,
			"id": "yEXyFeyTF_nda3Tig3v_u",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 698.3359375,
			"y": 362.6953125,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 209.55859375,
			"height": 93.796875,
			"seed": 171774811,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "X18S--uqTnEt_dXaKXJ7r",
					"type": "arrow"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 41,
			"versionNonce": 1536258559,
			"isDeleted": false,
			"id": "qT6eFwCy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 715,
			"y": 376,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 71.3199462890625,
			"height": 25,
			"seed": 555745429,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Guards",
			"rawText": "Guards",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Guards",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 77,
			"versionNonce": 1979052401,
			"isDeleted": false,
			"id": "d9Jf3N8CbULAEiz1H5ilx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 701.09375,
			"y": 469.97265625,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 203.6171875,
			"height": 92.89453125,
			"seed": 1065260091,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "CLIXwHBLEwke24wT5nqRF",
					"type": "arrow"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 42,
			"versionNonce": 28367391,
			"isDeleted": false,
			"id": "BW9u1umz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 724,
			"y": 488,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 77.39993286132812,
			"height": 25,
			"seed": 951247643,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Markers",
			"rawText": "Markers",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Markers",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 102,
			"versionNonce": 1375247185,
			"isDeleted": false,
			"id": "EOEUN0JlL68WSd1zIOdgm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 679.1328125,
			"y": 279.078125,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 68.12109375,
			"height": 91.5,
			"seed": 1318708891,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "nBMueA1qqfMuySkoII-ta",
				"focus": 0.8550383666107796,
				"gap": 12.6484375
			},
			"endBinding": {
				"elementId": "rLc-07RCyv7ZSkaOJjFKC",
				"focus": 0.3976291563418558,
				"gap": 9.5703125
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-68.12109375,
					91.5
				]
			]
		},
		{
			"type": "arrow",
			"version": 82,
			"versionNonce": 1542340159,
			"isDeleted": false,
			"id": "X18S--uqTnEt_dXaKXJ7r",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 683.91796875,
			"y": 407.125,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 74.640625,
			"height": 1.76171875,
			"seed": 964861531,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "yEXyFeyTF_nda3Tig3v_u",
				"focus": -0.006980237405878732,
				"gap": 14.41796875
			},
			"endBinding": {
				"elementId": "rLc-07RCyv7ZSkaOJjFKC",
				"focus": 0.0346822164932492,
				"gap": 7.8359375
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-74.640625,
					-1.76171875
				]
			]
		},
		{
			"type": "arrow",
			"version": 79,
			"versionNonce": 1338706225,
			"isDeleted": false,
			"id": "CLIXwHBLEwke24wT5nqRF",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 685.59375,
			"y": 518.44921875,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 74.4375,
			"height": 59.77734375,
			"seed": 1975042459,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "d9Jf3N8CbULAEiz1H5ilx",
				"focus": -0.7506289974090473,
				"gap": 15.5
			},
			"endBinding": {
				"elementId": "rLc-07RCyv7ZSkaOJjFKC",
				"focus": -0.10432258053072271,
				"gap": 9.71484375
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-74.4375,
					-59.77734375
				]
			]
		},
		{
			"type": "rectangle",
			"version": 271,
			"versionNonce": 757735007,
			"isDeleted": false,
			"id": "UTik35Yxoa-Sm1Qflqc7y",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -11.56640625,
			"y": 300.3515625,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 222.59765625,
			"height": 127.49609375,
			"seed": 668407547,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "o5pmn0oMt3pUkyQ9pg-tK",
					"type": "arrow"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 113,
			"versionNonce": 685030161,
			"isDeleted": false,
			"id": "txccHeZH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 25.94140625,
			"y": 333.76171875,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 113.11990356445312,
			"height": 50,
			"seed": 727556955,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Target App\n(JAR)",
			"rawText": "Target App\n(JAR)",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Target App\n(JAR)",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 628,
			"versionNonce": 137689727,
			"isDeleted": false,
			"id": "o5pmn0oMt3pUkyQ9pg-tK",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 221.859375,
			"y": 362.18134320121004,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 143.79296874999994,
			"height": 2.829300884859208,
			"seed": 1133877397,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "UTik35Yxoa-Sm1Qflqc7y",
				"focus": -0.06561926317263671,
				"gap": 10.828125
			},
			"endBinding": {
				"elementId": "kk64u9WEfepzCboSAfjuc",
				"focus": -0.01464556777338797,
				"gap": 22.796875
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					143.79296874999994,
					2.829300884859208
				]
			]
		},
		{
			"type": "text",
			"version": 89,
			"versionNonce": 2106405105,
			"isDeleted": false,
			"id": "GbFqGJUF",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 431.7109375,
			"y": 390.296875,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 127.45994567871094,
			"height": 25,
			"seed": 1799580123,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "GENERATED",
			"rawText": "GENERATED",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "GENERATED",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 45,
			"versionNonce": 1372541599,
			"isDeleted": false,
			"id": "UiBpapfC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 743,
			"y": 305,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 111.87991333007812,
			"height": 25,
			"seed": 207474005,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Generated?",
			"rawText": "Generated?",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Generated?",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 61,
			"versionNonce": 1937500881,
			"isDeleted": false,
			"id": "j1HLiNPj",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 748.20703125,
			"y": 414.67578125,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 111.87991333007812,
			"height": 25,
			"seed": 1085707061,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Generated?",
			"rawText": "Generated?",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Generated?",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 60,
			"versionNonce": 44672703,
			"isDeleted": false,
			"id": "1AMdz9eT",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 754.17578125,
			"y": 523.10546875,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 111.87991333007812,
			"height": 25,
			"seed": 1834799285,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Generated?",
			"rawText": "Generated?",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Generated?",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 169,
			"versionNonce": 322463921,
			"isDeleted": false,
			"id": "7E198lp2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 232.484375,
			"y": 271.5703125,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 122.35989379882812,
			"height": 75,
			"seed": 1101743611,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Argument\nfor launching\nDiSL",
			"rawText": "Argument\nfor launching\nDiSL",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Argument\nfor launching\nDiSL",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 99,
			"versionNonce": 694449887,
			"isDeleted": false,
			"id": "raLbizLkNRg5Mt0V-AZ3p",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 941.1982235393242,
			"y": 376.5962338341916,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.7292726091879,
			"height": 1.7367336836232425,
			"seed": 790108277,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "kk64u9WEfepzCboSAfjuc",
				"focus": 0.0306922149271646,
				"gap": 18.924786039324204
			},
			"endBinding": {
				"elementId": "vakHEt9htaMhgpccUrk9a",
				"focus": -0.0066717107188893916,
				"gap": 4.533034247622027
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					194.7292726091879,
					1.7367336836232425
				]
			]
		},
		{
			"type": "rectangle",
			"version": 107,
			"versionNonce": 1718233745,
			"isDeleted": false,
			"id": "vakHEt9htaMhgpccUrk9a",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1140.4605303961341,
			"y": 328.54925747670643,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 132.84419346063078,
			"height": 100.15695353445597,
			"seed": 1137010837,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "raLbizLkNRg5Mt0V-AZ3p",
					"type": "arrow"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 62,
			"versionNonce": 747651839,
			"isDeleted": false,
			"id": "lDyVJhmI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1159.6044342573573,
			"y": 365.5106149316148,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 96.67991638183594,
			"height": 25,
			"seed": 196925979,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Exit Code",
			"rawText": "Exit Code",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Exit Code",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 224,
			"versionNonce": 1439215729,
			"isDeleted": false,
			"id": "WAxnwy5CSv3gw1C_qf6rX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1040.3593435395926,
			"y": 377.6040173707896,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 107.90453843043565,
			"height": 132.027609962597,
			"seed": 1427723189,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"startBinding": null,
			"endBinding": {
				"elementId": "mW6gI3BfJqatDAG6DAyeq",
				"focus": 0.5087934125311974,
				"gap": 2.4537338282384553
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-3.0233506097936242,
					120.0059075378839
				],
				[
					104.88118782064203,
					132.027609962597
				]
			]
		},
		{
			"type": "rectangle",
			"version": 259,
			"versionNonce": 1286441759,
			"isDeleted": false,
			"id": "mW6gI3BfJqatDAG6DAyeq",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1147.694265188473,
			"y": 477.0559207630406,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 201.33762394205687,
			"height": 202.64814087304774,
			"seed": 251301781,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "WAxnwy5CSv3gw1C_qf6rX",
					"type": "arrow"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 134,
			"versionNonce": 1192983121,
			"isDeleted": false,
			"id": "tQnFL1jm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1163.2053683169797,
			"y": 505.2141097757303,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 187.4398193359375,
			"height": 125,
			"seed": 1371039477,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Instrumentation\nside effects\n(prints to stdout, \nstderr, writing to \nsockets, etc.)",
			"rawText": "Instrumentation\nside effects\n(prints to stdout, \nstderr, writing to \nsockets, etc.)",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Instrumentation\nside effects\n(prints to stdout, \nstderr, writing to \nsockets, etc.)",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 293,
			"versionNonce": 1714212671,
			"isDeleted": false,
			"id": "szHhHu7JWsQbTUKVmfYCQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -56.82554436929604,
			"y": 122.7852175352798,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 1465.255123166433,
			"height": 592.2737437394762,
			"seed": 2060786107,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 131,
			"versionNonce": 2067400753,
			"isDeleted": false,
			"id": "aKKCwRSE",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -26.65332417004163,
			"y": 144.7986619920016,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 136.69989013671875,
			"height": 25,
			"seed": 1968603931,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSL Workflow",
			"rawText": "DiSL Workflow",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "DiSL Workflow",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 201,
			"versionNonce": 361277489,
			"isDeleted": false,
			"id": "Iy1QKV89xjlqeiygg0fbz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -812.7282082402121,
			"y": -474.55787929421376,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 222.62865463844707,
			"height": 126.21857423162749,
			"seed": 143758747,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "OPGFvG6O"
				}
			],
			"updated": 1715088389790,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 152,
			"versionNonce": 1132886545,
			"isDeleted": false,
			"id": "OPGFvG6O",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -762.9738327032151,
			"y": -436.4485921784,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 123.11990356445312,
			"height": 50,
			"seed": 2080733467,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1715088389790,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Target App \n(JAR)",
			"rawText": "Target App \n(JAR)",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "Iy1QKV89xjlqeiygg0fbz",
			"originalText": "Target App \n(JAR)",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 308,
			"versionNonce": 1321421695,
			"isDeleted": false,
			"id": "BMV0arOwU91UJtqkrOFu-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -808.5555162414012,
			"y": -323.1519293518595,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 212.7214837990961,
			"height": 107.79593346099921,
			"seed": 552979349,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "Pu6HwSn1"
				}
			],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 195,
			"versionNonce": 1787326449,
			"isDeleted": false,
			"id": "Pu6HwSn1",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -777.2847096445875,
			"y": -294.2539626213599,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 150.17987060546875,
			"height": 50,
			"seed": 519309429,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729550,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Target Method\nIdentifier",
			"rawText": "Target Method Identifier",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "BMV0arOwU91UJtqkrOFu-",
			"originalText": "Target Method Identifier",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 194,
			"versionNonce": 879461279,
			"isDeleted": false,
			"id": "0Sv4W1O6HN0vZ7syvlomU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -811.7042395459198,
			"y": -192.92351687884764,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 215.15696038822523,
			"height": 108.29172690950054,
			"seed": 368114581,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "ifWqRkKt"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 147,
			"versionNonce": 1018644945,
			"isDeleted": false,
			"id": "ifWqRkKt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -782.1456873303227,
			"y": -163.77765342409737,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 156.03985595703125,
			"height": 50,
			"seed": 1762020693,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Level of\nInstrumentation",
			"rawText": "Level of Instrumentation",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "0Sv4W1O6HN0vZ7syvlomU",
			"originalText": "Level of Instrumentation",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 277,
			"versionNonce": 77051839,
			"isDeleted": false,
			"id": "a9l9dHSxCTBk6uin-jAk2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -827.8044794261997,
			"y": -557.227323959218,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 254.75085122292774,
			"height": 487.16490287126965,
			"seed": 485137045,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "X_FpA2G1GUuVwOXum3eTT",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 126,
			"versionNonce": 2075125681,
			"isDeleted": false,
			"id": "7kbhC9LT",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -795.7257734950963,
			"y": -526.7838665951014,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 52.75994873046875,
			"height": 25,
			"seed": 1751655733,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Input",
			"rawText": "Input",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Input",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 203,
			"versionNonce": 1164470239,
			"isDeleted": false,
			"id": "JcNDZlPzPxexIENAGLoWL",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -333.53106629536876,
			"y": -689.6027805005937,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 1985.5483836797116,
			"height": 750.6051866389836,
			"seed": 1097768315,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 44,
			"versionNonce": 1905904017,
			"isDeleted": false,
			"id": "6VirS6WP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -293.1978343532521,
			"y": -653.5229344587777,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 235.29977416992188,
			"height": 25,
			"seed": 1832304859,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Auto-Debugger Workflow",
			"rawText": "Auto-Debugger Workflow",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Auto-Debugger Workflow",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 110,
			"versionNonce": 1693324287,
			"isDeleted": false,
			"id": "ZpCxtbmu1CsvynF3oGLcJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -282.96013454823344,
			"y": -535.7241507209974,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 282.91539835007336,
			"height": 513.1288229375232,
			"seed": 609708795,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "FDRB-ZZpJWOVIoGHykDgZ",
					"type": "arrow"
				},
				{
					"id": "QAx28TsQfU_0QWeFTobtS",
					"type": "arrow"
				},
				{
					"id": "xtknmnux-tHJUom6fFw88",
					"type": "arrow"
				},
				{
					"id": "cHo0VIwDUxif8JRnPqzbW",
					"type": "arrow"
				},
				{
					"id": "VdlsHEpww0MtkthyQeIWL",
					"type": "arrow"
				},
				{
					"id": "X_FpA2G1GUuVwOXum3eTT",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 32,
			"versionNonce": 934047601,
			"isDeleted": false,
			"id": "lJlw8FfD",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -253.11684820072367,
			"y": -497.65243275450075,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 58.17994689941406,
			"height": 25,
			"seed": 1684800699,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "runner",
			"rawText": "runner",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "runner",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 61,
			"versionNonce": 992558111,
			"isDeleted": false,
			"id": "Uq1HYgCJmIQySs5ox-cn2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -258.0399901630351,
			"y": -452.8744560372229,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 234.379829180996,
			"height": 119.59059865692552,
			"seed": 531133301,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 36,
			"versionNonce": 1412389201,
			"isDeleted": false,
			"id": "loMUxgbH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -233.0763551244595,
			"y": -417.49046044944396,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 152.8998565673828,
			"height": 25,
			"seed": 1716228757,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "ArgumentParser",
			"rawText": "ArgumentParser",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "ArgumentParser",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 89,
			"versionNonce": 1275748415,
			"isDeleted": false,
			"id": "vVC7-wlLA2S1L-7ofxMF3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 157.20876828351538,
			"y": -650.9917784322183,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 446.30978308862495,
			"height": 186.98371513040422,
			"seed": 795780219,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "MgwOB-7qqNo6DgHeABRF5",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 86,
			"versionNonce": 410650417,
			"isDeleted": false,
			"id": "2gIqyWJC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 174.46585954363195,
			"y": -626.9153527225399,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.8397979736328,
			"height": 25,
			"seed": 1842033141,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "instrumentor-common",
			"rawText": "instrumentor-common",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "instrumentor-common",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 129,
			"versionNonce": 1232207967,
			"isDeleted": false,
			"id": "iOyMyC1gmT_xd5Zfn7Sh-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 162.4667882768324,
			"y": -427.3758859621064,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 446.30978308862495,
			"height": 186.98371513040422,
			"seed": 1221880603,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "FDRB-ZZpJWOVIoGHykDgZ",
					"type": "arrow"
				},
				{
					"id": "qJCsTB9aJ11j55Nb8E1Ce",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 59,
			"versionNonce": 1056176401,
			"isDeleted": false,
			"id": "VAqSuA1e",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 194.28020122233397,
			"y": -393.518412307871,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 171.67984008789062,
			"height": 25,
			"seed": 805526267,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "instrumentor-java",
			"rawText": "instrumentor-java",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "instrumentor-java",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 259,
			"versionNonce": 1429249151,
			"isDeleted": false,
			"id": "anSaicdPuTq7t5YaWEZ9k",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 169.9123881350274,
			"y": -25.64402258522398,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 434.90653377309445,
			"height": 55.75501762971089,
			"seed": 1611585787,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "VdlsHEpww0MtkthyQeIWL",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 121,
			"versionNonce": 1226092273,
			"isDeleted": false,
			"id": "kDZoHnVy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 184.9905976609407,
			"y": -8.600035526656029,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 138.2998504638672,
			"height": 25,
			"seed": 1602044443,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "instrumentor-*",
			"rawText": "instrumentor-*",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "instrumentor-*",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 133,
			"versionNonce": 50771103,
			"isDeleted": false,
			"id": "pTCnTHI6fS9RM0C1_hGLQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 172.29132687476636,
			"y": -215.33720542103424,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 428.91352173770133,
			"height": 52.58889806384252,
			"seed": 968362165,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "b0d3ICuo"
				},
				{
					"id": "QAx28TsQfU_0QWeFTobtS",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 25,
			"versionNonce": 493070545,
			"isDeleted": false,
			"id": "b0d3ICuo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 378.5280941523084,
			"y": -201.54275638911298,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 222137275,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "pTCnTHI6fS9RM0C1_hGLQ",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 169,
			"versionNonce": 447006911,
			"isDeleted": false,
			"id": "K87vRgjcO1BIoAO1fQA_v",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 170.67782363446838,
			"y": -146.65676560758715,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 428.91352173770133,
			"height": 52.58889806384252,
			"seed": 1918544347,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "k4TA8wPX"
				},
				{
					"id": "xtknmnux-tHJUom6fFw88",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 24,
			"versionNonce": 1702389425,
			"isDeleted": false,
			"id": "k4TA8wPX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 376.91459091201045,
			"y": -132.8623165756659,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 1035869211,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "K87vRgjcO1BIoAO1fQA_v",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 218,
			"versionNonce": 430659807,
			"isDeleted": false,
			"id": "pzfeTtPwEcW7N7SEg_wtn",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 171.66941053147082,
			"y": -85.54369948179198,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 428.91352173770133,
			"height": 52.58889806384252,
			"seed": 411088181,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "eFCz8dFn"
				},
				{
					"id": "cHo0VIwDUxif8JRnPqzbW",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 24,
			"versionNonce": 737831057,
			"isDeleted": false,
			"id": "eFCz8dFn",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 377.9061778090129,
			"y": -71.74925044987071,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 200726651,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "pzfeTtPwEcW7N7SEg_wtn",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 124,
			"versionNonce": 1974134015,
			"isDeleted": false,
			"id": "FDRB-ZZpJWOVIoGHykDgZ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 12.0804579634339,
			"y": -266.46481753139915,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 144.64991313293126,
			"height": 55.39839427201673,
			"seed": 1750365429,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ZpCxtbmu1CsvynF3oGLcJ",
				"focus": 0.23014235567809818,
				"gap": 12.125194161593981
			},
			"endBinding": {
				"elementId": "iOyMyC1gmT_xd5Zfn7Sh-",
				"focus": 0.42267619282704244,
				"gap": 5.736417180467242
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					144.64991313293126,
					-55.39839427201673
				]
			]
		},
		{
			"type": "arrow",
			"version": 119,
			"versionNonce": 638391871,
			"isDeleted": false,
			"id": "QAx28TsQfU_0QWeFTobtS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 12.68932711071622,
			"y": -243.9719889694832,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 150.34718872535862,
			"height": 53.85764828978628,
			"seed": 265163125,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329418,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ZpCxtbmu1CsvynF3oGLcJ",
				"gap": 12.73406330887633,
				"focus": -0.06524951715174936
			},
			"endBinding": {
				"elementId": "pTCnTHI6fS9RM0C1_hGLQ",
				"gap": 9.254811038691514,
				"focus": -0.766763674164462
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					150.34718872535862,
					53.85764828978628
				]
			]
		},
		{
			"type": "arrow",
			"version": 131,
			"versionNonce": 1249759871,
			"isDeleted": false,
			"id": "xtknmnux-tHJUom6fFw88",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 15.95526380183992,
			"y": -217.25724318906708,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 148.40336789690514,
			"height": 95.19489286967367,
			"seed": 1825623771,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ZpCxtbmu1CsvynF3oGLcJ",
				"gap": 16,
				"focus": -0.11258263633478209
			},
			"endBinding": {
				"elementId": "K87vRgjcO1BIoAO1fQA_v",
				"gap": 6.319191935723325,
				"focus": -0.8538937284738344
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					148.40336789690514,
					95.19489286967367
				]
			]
		},
		{
			"type": "arrow",
			"version": 104,
			"versionNonce": 983334591,
			"isDeleted": false,
			"id": "cHo0VIwDUxif8JRnPqzbW",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 15.629295279022244,
			"y": -182.98354259844143,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 148.7293364197228,
			"height": 128.57771483059906,
			"seed": 1380165147,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ZpCxtbmu1CsvynF3oGLcJ",
				"gap": 15.674031477182325,
				"focus": -0.10469804757215892
			},
			"endBinding": {
				"elementId": "pzfeTtPwEcW7N7SEg_wtn",
				"gap": 7.3107788327257595,
				"focus": -0.9285251588589369
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					148.7293364197228,
					128.57771483059906
				]
			]
		},
		{
			"type": "arrow",
			"version": 90,
			"versionNonce": 1049030975,
			"isDeleted": false,
			"id": "VdlsHEpww0MtkthyQeIWL",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 15.35095509740745,
			"y": -154.6938383517139,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 142.94507952054073,
			"height": 161.86351373967034,
			"seed": 1951015035,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "ZpCxtbmu1CsvynF3oGLcJ",
				"focus": -0.12752839642410244,
				"gap": 15.39569129556753
			},
			"endBinding": {
				"elementId": "anSaicdPuTq7t5YaWEZ9k",
				"focus": -0.9642933010272317,
				"gap": 11.616353517079233
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					142.94507952054073,
					161.86351373967034
				]
			]
		},
		{
			"type": "rectangle",
			"version": 238,
			"versionNonce": 109774385,
			"isDeleted": false,
			"id": "NJRRdAJ2XNfs_jrVw-Y1-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 136.5420097986181,
			"y": -450.13454487445244,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 503.17816144479474,
			"height": 490.1570598236284,
			"seed": 876313275,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "MgwOB-7qqNo6DgHeABRF5",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "arrow",
			"version": 454,
			"versionNonce": 1077178719,
			"isDeleted": false,
			"id": "MgwOB-7qqNo6DgHeABRF5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 642.0338740030858,
			"y": -211.22204476407768,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 114.92840061487777,
			"height": 347.42168028676167,
			"seed": 311514677,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "NJRRdAJ2XNfs_jrVw-Y1-",
				"gap": 2.3137027596728785,
				"focus": 0.7777753781200537
			},
			"endBinding": {
				"elementId": "vVC7-wlLA2S1L-7ofxMF3",
				"focus": -0.5722318924492322,
				"gap": 9.402679260174182
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					85.81575724410652,
					-290.04011222073973
				],
				[
					-29.112643370771252,
					-347.42168028676167
				]
			]
		},
		{
			"type": "arrow",
			"version": 626,
			"versionNonce": 1619859473,
			"isDeleted": false,
			"id": "qJCsTB9aJ11j55Nb8E1Ce",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 612.808154933534,
			"y": -347.0322461212538,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 198.29998313917997,
			"height": 1.1010126999113936,
			"seed": 259107963,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "iOyMyC1gmT_xd5Zfn7Sh-",
				"focus": -0.1254798950059014,
				"gap": 4.031583568076599
			},
			"endBinding": {
				"elementId": "4a5xmEDp0ffrEp1Xwyq7I",
				"focus": 0.9896660180945644,
				"gap": 24.78097429439231
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					198.29998313917997,
					-1.1010126999113936
				]
			]
		},
		{
			"type": "rectangle",
			"version": 260,
			"versionNonce": 209513855,
			"isDeleted": false,
			"id": "pISrWzURV4AsElgbkN-q7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 818.7277005444187,
			"y": -647.3907523325773,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 349.5430793241112,
			"height": 620.3419816432666,
			"seed": 2043345013,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "qJCsTB9aJ11j55Nb8E1Ce",
					"type": "arrow"
				},
				{
					"id": "UHEQ-ox6eIRBIrnGtRLzm",
					"type": "arrow"
				},
				{
					"id": "oEi6fgyQF_4sR9sP-zbwM",
					"type": "arrow"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 159,
			"versionNonce": 942796273,
			"isDeleted": false,
			"id": "VYFk6CQn",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 841.6820673969626,
			"y": -621.757361231991,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 121.73988342285156,
			"height": 25,
			"seed": 1793134619,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "analyzer-disl",
			"rawText": "analyzer-disl",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "analyzer-disl",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 169,
			"versionNonce": 245425567,
			"isDeleted": false,
			"id": "ZAXfuMefOIxLvvhZmDlCy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 843.9000907192051,
			"y": -327.1777696461231,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 293.27487198454855,
			"height": 60.49549884783744,
			"seed": 1306528827,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "fFknYP3k"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 120,
			"versionNonce": 1417742289,
			"isDeleted": false,
			"id": "fFknYP3k",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 941.8775688257372,
			"y": -309.4300202222044,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 97.31991577148438,
			"height": 25,
			"seed": 1141806011,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSLClass",
			"rawText": "DiSLClass",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "ZAXfuMefOIxLvvhZmDlCy",
			"originalText": "DiSLClass",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 255,
			"versionNonce": 1976621503,
			"isDeleted": false,
			"id": "BXxU4iZuV86OvzJs8T13m",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 846.370359831036,
			"y": -242.40578808307157,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 291.72660472431653,
			"height": 47.29173648248627,
			"seed": 1610776027,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "yohYlRWd"
				}
			],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 165,
			"versionNonce": 798090673,
			"isDeleted": false,
			"id": "yohYlRWd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 916.9337278059872,
			"y": -231.25991984182843,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 150.59986877441406,
			"height": 25,
			"seed": 1260039637,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729551,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Static Context",
			"rawText": "Static Context",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "BXxU4iZuV86OvzJs8T13m",
			"originalText": "Static Context",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 169,
			"versionNonce": 925714911,
			"isDeleted": false,
			"id": "Z5ENkzY52oexn_v-dKg3O",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 845.7093018997018,
			"y": -173.45570621868512,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 294.84053550613135,
			"height": 48.32681403286631,
			"seed": 2093541243,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "Xhdf0pjf"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 82,
			"versionNonce": 344721297,
			"isDeleted": false,
			"id": "Xhdf0pjf",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 957.4695965082362,
			"y": -161.79229920225197,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 71.3199462890625,
			"height": 25,
			"seed": 809614363,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Guards",
			"rawText": "Guards",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "Z5ENkzY52oexn_v-dKg3O",
			"originalText": "Guards",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 171,
			"versionNonce": 1338422783,
			"isDeleted": false,
			"id": "5sbqrfg2iOa-lYbiK9kDh",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 843.726128105696,
			"y": -102.6094318670481,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 291.343886974596,
			"height": 50.44045978700353,
			"seed": 2108471157,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "npQt6mzt"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 83,
			"versionNonce": 2108091761,
			"isDeleted": false,
			"id": "npQt6mzt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 950.69810516233,
			"y": -89.88920197354634,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 77.39993286132812,
			"height": 25,
			"seed": 1672692949,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Markers",
			"rawText": "Markers",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "5sbqrfg2iOa-lYbiK9kDh",
			"originalText": "Markers",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 147,
			"versionNonce": 1184734751,
			"isDeleted": false,
			"id": "4a5xmEDp0ffrEp1Xwyq7I",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 835.8891123671062,
			"y": -349.8624944477285,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 314.2982538271399,
			"height": 306.29597360571483,
			"seed": 1706012501,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "mABUaLoE2kGn_5fJLAPjZ",
					"type": "arrow"
				},
				{
					"id": "aWhXIPR9dNmJEx3LIXy4v",
					"type": "arrow"
				},
				{
					"id": "qJCsTB9aJ11j55Nb8E1Ce",
					"type": "arrow"
				},
				{
					"id": "UHEQ-ox6eIRBIrnGtRLzm",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "arrow",
			"version": 547,
			"versionNonce": 841619281,
			"isDeleted": false,
			"id": "mABUaLoE2kGn_5fJLAPjZ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 975.0331087824702,
			"y": -20.751324080276845,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 292.21370004214236,
			"height": 179.67728536301615,
			"seed": 628687355,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "4a5xmEDp0ffrEp1Xwyq7I",
				"focus": -0.7944807345248938,
				"gap": 22.815196761736843
			},
			"endBinding": {
				"elementId": "kk64u9WEfepzCboSAfjuc",
				"focus": -0.178727865198554,
				"gap": 1
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-256.11645773897567,
					97.33208225841895
				],
				[
					-292.21370004214236,
					179.67728536301615
				]
			]
		},
		{
			"type": "rectangle",
			"version": 204,
			"versionNonce": 1856143935,
			"isDeleted": false,
			"id": "5BYEZ9wGb6YR8NRN6Qh0A",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 840.9949150736027,
			"y": -573.9089443862758,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 285.994536609187,
			"height": 62.25252124428073,
			"seed": 531731259,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "OXToPDiu"
				},
				{
					"id": "aWhXIPR9dNmJEx3LIXy4v",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 119,
			"versionNonce": 611124529,
			"isDeleted": false,
			"id": "OXToPDiu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 909.1322590617899,
			"y": -555.2826837641355,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 149.7198486328125,
			"height": 25,
			"seed": 1572772283,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "ResultCollector",
			"rawText": "ResultCollector",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "5BYEZ9wGb6YR8NRN6Qh0A",
			"originalText": "ResultCollector",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 278,
			"versionNonce": 2007833343,
			"isDeleted": false,
			"id": "aWhXIPR9dNmJEx3LIXy4v",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 983.3095869398478,
			"y": -356.9949616016074,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 4.082863354622759,
			"height": 153.0262129734009,
			"seed": 320889467,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "4a5xmEDp0ffrEp1Xwyq7I",
				"gap": 7.1324671538787925,
				"focus": -0.08686106575845608
			},
			"endBinding": {
				"elementId": "5BYEZ9wGb6YR8NRN6Qh0A",
				"gap": 1.6352485669868884,
				"focus": -0.029718679184860906
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					4.082863354622759,
					-153.0262129734009
				]
			]
		},
		{
			"type": "rectangle",
			"version": 100,
			"versionNonce": 265762577,
			"isDeleted": false,
			"id": "HzzyMC9qBKMyrspcyyCO0",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1256.3132585655565,
			"y": -642.9547056880923,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 356.2493380748929,
			"height": 158.76697921920584,
			"seed": 1994280027,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "E88PA2vE7xkdDhYWsDkp3",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 67,
			"versionNonce": 1705524863,
			"isDeleted": false,
			"id": "PVO4iRyP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1284.495201954053,
			"y": -615.5121034070102,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 221.85977172851562,
			"height": 25,
			"seed": 1425529595,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "test-generator-common",
			"rawText": "test-generator-common",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "test-generator-common",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 235,
			"versionNonce": 1457532145,
			"isDeleted": false,
			"id": "G0DNXbcHn-00XkkZ4plyp",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1266.0203723993716,
			"y": -386.19458627913286,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 349.13426718236497,
			"height": 139.00482632455646,
			"seed": 1525061717,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "UHEQ-ox6eIRBIrnGtRLzm",
					"type": "arrow"
				},
				{
					"id": "FvmV1XuQmPFMRkM_qOTTS",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 131,
			"versionNonce": 674895519,
			"isDeleted": false,
			"id": "uJk0tCAs",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1287.8613785254568,
			"y": -364.8058829481721,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 198.69981384277344,
			"height": 25,
			"seed": 1149495195,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "test-generator-java",
			"rawText": "test-generator-java",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "test-generator-java",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 584,
			"versionNonce": 1493220785,
			"isDeleted": false,
			"id": "v2gp_w22UXf64Rdnh9B-O",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1261.3860555890333,
			"y": -33.861630732629976,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 350.61562213639456,
			"height": 53.180370949774215,
			"seed": 1229753045,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714424870903,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 431,
			"versionNonce": 1193011903,
			"isDeleted": false,
			"id": "ojYBFmjG",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1280.9626387305468,
			"y": -17.6365583610596,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 165.31982421875,
			"height": 25,
			"seed": 1367238709,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "test-generator-*",
			"rawText": "test-generator-*",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "test-generator-*",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 478,
			"versionNonce": 956064945,
			"isDeleted": false,
			"id": "8rYLib7QHxxo_c5Xjhyn-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1265.859456981876,
			"y": -221.74120413084376,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 349.99538211923556,
			"height": 51.48423546805884,
			"seed": 642619797,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "uU5A5SWe"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 521,
			"versionNonce": 787364575,
			"isDeleted": false,
			"id": "uU5A5SWe",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1432.6371544501853,
			"y": -208.49908639681433,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 1894751989,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "8rYLib7QHxxo_c5Xjhyn-",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 463,
			"versionNonce": 709797521,
			"isDeleted": false,
			"id": "Ob7q-Iu1i8Zr7MgSllK_m",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1263.8980285145594,
			"y": -155.79197734949162,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 352.8222745887609,
			"height": 47.2308495677579,
			"seed": 401209429,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "2SwRLGcR"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 468,
			"versionNonce": 1682015999,
			"isDeleted": false,
			"id": "2SwRLGcR",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1432.0891722176314,
			"y": -144.67655256561267,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 353510837,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "Ob7q-Iu1i8Zr7MgSllK_m",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 536,
			"versionNonce": 532524145,
			"isDeleted": false,
			"id": "7UR7Eaa1iNU7gjxpiyuUu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1263.1760836684962,
			"y": -94.67891122369645,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 348.0817933706339,
			"height": 45.369449603209326,
			"seed": 193832725,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "LYz2ITZ2"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 492,
			"versionNonce": 93736735,
			"isDeleted": false,
			"id": "LYz2ITZ2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1428.9969867625045,
			"y": -84.49418642209179,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 500300917,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "7UR7Eaa1iNU7gjxpiyuUu",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 120,
			"versionNonce": 17888849,
			"isDeleted": false,
			"id": "dxz7dXlxsMBZ09y3gxvK1",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1252.4164960229498,
			"y": -404.634623311112,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 376.8030208610089,
			"height": 439.33388228690444,
			"seed": 666672475,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "E88PA2vE7xkdDhYWsDkp3",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "arrow",
			"version": 176,
			"versionNonce": 224203583,
			"isDeleted": false,
			"id": "E88PA2vE7xkdDhYWsDkp3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1630.9765392804018,
			"y": -165.46212412793648,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 125.15740228922095,
			"height": 405.77649414097255,
			"seed": 1579827317,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "dxz7dXlxsMBZ09y3gxvK1",
				"focus": 0.7511510500217494,
				"gap": 1.7570223964430625
			},
			"endBinding": {
				"elementId": "HzzyMC9qBKMyrspcyyCO0",
				"focus": -0.6060477671384309,
				"gap": 6.9759008017204
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					113.71936045098892,
					-340.16649445596363
				],
				[
					-11.438041838232039,
					-405.77649414097255
				]
			]
		},
		{
			"type": "arrow",
			"version": 195,
			"versionNonce": 1361052721,
			"isDeleted": false,
			"id": "UHEQ-ox6eIRBIrnGtRLzm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1175.7337759880772,
			"y": -332.77936580112146,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 85.9723235962656,
			"height": 1.4177953001003516,
			"seed": 1691214011,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "4a5xmEDp0ffrEp1Xwyq7I",
				"focus": -0.8930147676213379,
				"gap": 25.546409793831117
			},
			"endBinding": {
				"elementId": "G0DNXbcHn-00XkkZ4plyp",
				"focus": 0.1619122396233471,
				"gap": 4.314272815028744
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					85.9723235962656,
					1.4177953001003516
				]
			]
		},
		{
			"type": "rectangle",
			"version": 425,
			"versionNonce": 1408652127,
			"isDeleted": false,
			"id": "mkrtDuNVx0sGI1RNCct6S",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1989.6526558137537,
			"y": -754.7489495767028,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 1041.7664128693946,
			"height": 811.7850081270759,
			"seed": 1482031509,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 166,
			"versionNonce": 1272075793,
			"isDeleted": false,
			"id": "loNdi1FCFA6Hy3RZ8ekal",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2030.0815671932999,
			"y": -656.6542615019524,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 342.2366495567238,
			"height": 163.7944987496228,
			"seed": 1478137467,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "oEi6fgyQF_4sR9sP-zbwM",
					"type": "arrow"
				},
				{
					"id": "osz2ygTO65wmaa4aYe_Ks",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 37,
			"versionNonce": 1370656639,
			"isDeleted": false,
			"id": "xKsZiXjW",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2051.5398555696643,
			"y": -629.0289984766855,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 185.8798065185547,
			"height": 25,
			"seed": 826593525,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "test-runner-common",
			"rawText": "test-runner-common",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "test-runner-common",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 295,
			"versionNonce": 1142909937,
			"isDeleted": false,
			"id": "_H1z6Tv6148I02Ld8oa-5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2044.9553706483405,
			"y": -395.1841043322298,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 323.5530648658312,
			"height": 163.7944987496228,
			"seed": 1669752315,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "FvmV1XuQmPFMRkM_qOTTS",
					"type": "arrow"
				},
				{
					"id": "MmZxKiBPXPIRNRURaOXHc",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 154,
			"versionNonce": 550651807,
			"isDeleted": false,
			"id": "xYXlpPkJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2065.717808570667,
			"y": -368.9548912803748,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 162.7198486328125,
			"height": 25,
			"seed": 824148123,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "test-runner-java",
			"rawText": "test-runner-java",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "test-runner-java",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 255,
			"versionNonce": 1108264401,
			"isDeleted": false,
			"id": "xGUEBmKYC_wmOHNZYZR_c",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2019.0523374968145,
			"y": -410.91032459346525,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 376.8030208610089,
			"height": 447.1621998948202,
			"seed": 468995957,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "osz2ygTO65wmaa4aYe_Ks",
					"type": "arrow"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "rectangle",
			"version": 656,
			"versionNonce": 1065745343,
			"isDeleted": false,
			"id": "TvwUmfcDjjglzg9n3WUUa",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2034.7089727126452,
			"y": -29.495119941792893,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 348.3688316829245,
			"height": 52.06701022331504,
			"seed": 82901301,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 564,
			"versionNonce": 1622664113,
			"isDeleted": false,
			"id": "Fy5O6bc6PgP6WypYw4SxP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2039.5320861721889,
			"y": -212.1689113225056,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 341.7495542388977,
			"height": 51.48423546805884,
			"seed": 1329990293,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "qeuCRoTj"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 607,
			"versionNonce": 300821471,
			"isDeleted": false,
			"id": "qeuCRoTj",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2202.186869700329,
			"y": -198.9267935884762,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 598863861,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "Fy5O6bc6PgP6WypYw4SxP",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 563,
			"versionNonce": 1315922719,
			"isDeleted": false,
			"id": "x-mBuIcUZk9q1BItOeGlx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2037.570657704872,
			"y": -146.21968454115347,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 340.50572155230685,
			"height": 46.630678551150936,
			"seed": 1077553493,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "eXZmnJCd"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 554,
			"versionNonce": 605771775,
			"isDeleted": false,
			"id": "eXZmnJCd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2199.603524889717,
			"y": -135.404345265578,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 104085173,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "x-mBuIcUZk9q1BItOeGlx",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 639,
			"versionNonce": 1071999857,
			"isDeleted": false,
			"id": "VKgIf_hqH2BK2NcQ4mbDr",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2036.8487128588094,
			"y": -85.1066184153583,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 341.7843467615994,
			"height": 44.79537297862897,
			"seed": 272996373,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "TnAK3ERV"
				}
			],
			"updated": 1714390729552,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 596,
			"versionNonce": 739115039,
			"isDeleted": false,
			"id": "TnAK3ERV",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2199.5208926483006,
			"y": -75.20893192604382,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 16.439987182617188,
			"height": 25,
			"seed": 140758389,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "...",
			"rawText": "...",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "VKgIf_hqH2BK2NcQ4mbDr",
			"originalText": "...",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 79,
			"versionNonce": 1676203345,
			"isDeleted": false,
			"id": "n34vHhPc",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2062.10231423854,
			"y": -15.936470272475617,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 129.33985900878906,
			"height": 25,
			"seed": 869181467,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "test-runner-*",
			"rawText": "test-runner-*",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "test-runner-*",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 314,
			"versionNonce": 1270260799,
			"isDeleted": false,
			"id": "oEi6fgyQF_4sR9sP-zbwM",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2199.7299078875085,
			"y": -661.4208371121067,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 1155.5031695817345,
			"height": 117.23340524387538,
			"seed": 1906363547,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "loNdi1FCFA6Hy3RZ8ekal",
				"focus": 0.8644043961989053,
				"gap": 4.766575610154291
			},
			"endBinding": {
				"elementId": "pISrWzURV4AsElgbkN-q7",
				"focus": -0.5918757152512565,
				"gap": 4.966632615698018
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-1018.116195562814,
					-108.16995308004402
				],
				[
					-1155.5031695817345,
					9.063452163831357
				]
			]
		},
		{
			"type": "arrow",
			"version": 273,
			"versionNonce": 1805910833,
			"isDeleted": false,
			"id": "osz2ygTO65wmaa4aYe_Ks",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2399.708630247053,
			"y": -201.9072916581306,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 103.15113168030257,
			"height": 384.98796182661863,
			"seed": 1101353749,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "xGUEBmKYC_wmOHNZYZR_c",
				"focus": 0.784802168758888,
				"gap": 3.853271889229859
			},
			"endBinding": {
				"elementId": "loNdi1FCFA6Hy3RZ8ekal",
				"focus": -0.5819709078409983,
				"gap": 2.6355335946647074
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					78.39625177793778,
					-335.58257958999513
				],
				[
					-24.75487990236479,
					-384.98796182661863
				]
			]
		},
		{
			"type": "arrow",
			"version": 146,
			"versionNonce": 318096479,
			"isDeleted": false,
			"id": "FvmV1XuQmPFMRkM_qOTTS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1628.367100077768,
			"y": -316.1572380803225,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 412.91765942552684,
			"height": 0.608869147282121,
			"seed": 469090107,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "G0DNXbcHn-00XkkZ4plyp",
				"focus": 0.0036990345918046846,
				"gap": 13.212460496031554
			},
			"endBinding": {
				"elementId": "_H1z6Tv6148I02Ld8oa-5",
				"focus": 0.024563629807369317,
				"gap": 3.6706111450454273
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					412.91765942552684,
					0.608869147282121
				]
			]
		},
		{
			"type": "rectangle",
			"version": 136,
			"versionNonce": 327054609,
			"isDeleted": false,
			"id": "djBLzX4rAf4EWBbfN7E8q",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2495.4822575486637,
			"y": -663.2249075663846,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 509.5418828592856,
			"height": 695.660081295291,
			"seed": 1297138613,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 20,
			"versionNonce": 1100734591,
			"isDeleted": false,
			"id": "6uho3E3P",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2521.5865476892536,
			"y": -636.8973063891937,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 25.17999267578125,
			"height": 25,
			"seed": 2086156469,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "UI",
			"rawText": "UI",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "UI",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 90,
			"versionNonce": 519023345,
			"isDeleted": false,
			"id": "VzqLrafgGx-KEPL0wOsL-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2521.8021583452824,
			"y": -568.3100166304224,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 463.6630154086556,
			"height": 116.41435349422977,
			"seed": 1734718363,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "MmZxKiBPXPIRNRURaOXHc",
					"type": "arrow"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 33,
			"versionNonce": 1231648927,
			"isDeleted": false,
			"id": "ChALvbm1",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2551.156009087444,
			"y": -548.1889221946225,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 149.8998565673828,
			"height": 25,
			"seed": 1206191419,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "TraceVisualizer",
			"rawText": "TraceVisualizer",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "TraceVisualizer",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 191,
			"versionNonce": 1988043985,
			"isDeleted": false,
			"id": "MmZxKiBPXPIRNRURaOXHc",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2377.004202061019,
			"y": -321.4358154778224,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 140.79375838659143,
			"height": 142.9498649468767,
			"seed": 1770384469,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "_H1z6Tv6148I02Ld8oa-5",
				"focus": 0.6692263371865593,
				"gap": 8.495766546847449
			},
			"endBinding": {
				"elementId": "VzqLrafgGx-KEPL0wOsL-",
				"focus": 0.6598685930928875,
				"gap": 4.004197897671702
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					140.79375838659143,
					-142.9498649468767
				]
			]
		},
		{
			"type": "text",
			"version": 47,
			"versionNonce": 269421759,
			"isDeleted": false,
			"id": "heqZelud",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2042.5612730385697,
			"y": -723.6343931572186,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 150.07984924316406,
			"height": 25,
			"seed": 658266069,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Test Visualizer",
			"rawText": "Test Visualizer",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Test Visualizer",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 109,
			"versionNonce": 96756401,
			"isDeleted": false,
			"id": "X_FpA2G1GUuVwOXum3eTT",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -572.5945285490823,
			"y": -281.8084610629205,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 278.2905974823482,
			"height": 3.092530087148816,
			"seed": 179218261,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "a9l9dHSxCTBk6uin-jAk2",
				"focus": 0.13574394477211907,
				"gap": 1
			},
			"endBinding": {
				"elementId": "ZpCxtbmu1CsvynF3oGLcJ",
				"focus": 0.02881915710974467,
				"gap": 11.343796518500653
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					278.2905974823482,
					-3.092530087148816
				]
			]
		},
		{
			"type": "rectangle",
			"version": 332,
			"versionNonce": 32745279,
			"isDeleted": false,
			"id": "V10GQfoWxFSHXEdyBAQwF",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 206.08472139933986,
			"y": -356.75939562602673,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 159.93836340160425,
			"height": 98.6296906817633,
			"seed": 1425027899,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "wsaP9FBK"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 136,
			"versionNonce": 546989919,
			"isDeleted": false,
			"id": "wsaP9FBK",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 214.89396047318886,
			"y": -319.94455028514506,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 142.31988525390625,
			"height": 25,
			"seed": 1270244661,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "InstGenerator",
			"rawText": "InstGenerator",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "V10GQfoWxFSHXEdyBAQwF",
			"originalText": "InstGenerator",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 279,
			"versionNonce": 5515519,
			"isDeleted": false,
			"id": "N_W4LwFHjeKxzwEW6wvGT",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 410.72594150782675,
			"y": -356.2678591982643,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 156.8253264263749,
			"height": 97.33665114155428,
			"seed": 955783893,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "SheNTpb8"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"customData": {
				"legacyTextWrap": true
			}
		},
		{
			"type": "text",
			"version": 142,
			"versionNonce": 2099128945,
			"isDeleted": false,
			"id": "SheNTpb8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 430.10866697687356,
			"y": -320.0995336274872,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 118.05987548828125,
			"height": 25,
			"seed": 33756635,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "InstCompiler",
			"rawText": "InstCompiler",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "N_W4LwFHjeKxzwEW6wvGT",
			"originalText": "InstCompiler",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 550,
			"versionNonce": 2098925727,
			"isDeleted": false,
			"id": "dbv4sTBZtpKEAq-maJT_7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -302.1578045072098,
			"y": -2239.429518875854,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 494.5949124956406,
			"height": 954.583893865281,
			"seed": 2021432384,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714421963746,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 193,
			"versionNonce": 164471999,
			"isDeleted": false,
			"id": "hn57Ldc9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -270.0541510813599,
			"y": -2206.6290441187275,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 416.71966552734375,
			"height": 25,
			"seed": 325680192,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714421963746,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSL Instrumentation Generating Workflow",
			"rawText": "DiSL Instrumentation Generating Workflow",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "DiSL Instrumentation Generating Workflow",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 823,
			"versionNonce": 1733866719,
			"isDeleted": false,
			"id": "wl8CMxzKm3eTel8-SUckl",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -197.40610450572342,
			"y": -2143.0563236730754,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 261.06423738556737,
			"height": 309.4619243282191,
			"seed": 1020897216,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "n6XuORC8sbkgD7HUp220b",
					"type": "arrow"
				},
				{
					"id": "uUN3CXbm2ik0V_-z6NHp-",
					"type": "arrow"
				}
			],
			"updated": 1714421963746,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 862,
			"versionNonce": 341953855,
			"isDeleted": false,
			"id": "gDtu4ugrTFIU2rFa6Kwv6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -163.52300380688473,
			"y": -1702.5812389513362,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 224.10568616156525,
			"height": 132.64767138771913,
			"seed": 2108922944,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "8LyJdr4d"
				},
				{
					"id": "BFS599T3iYunztGIY3yLI",
					"type": "arrow"
				},
				{
					"id": "uUN3CXbm2ik0V_-z6NHp-",
					"type": "arrow"
				}
			],
			"updated": 1714421963746,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 817,
			"versionNonce": 1314114911,
			"isDeleted": false,
			"id": "8LyJdr4d",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -89.30011678078961,
			"y": -1648.7574032574767,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 75.659912109375,
			"height": 25,
			"seed": 1970672704,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714421963746,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Compiler",
			"rawText": "Compiler",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "gDtu4ugrTFIU2rFa6Kwv6",
			"originalText": "Compiler",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1059,
			"versionNonce": 1230878143,
			"isDeleted": false,
			"id": "TMgrKhbRxyfdZ7y6jRI3l",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -157.57314123783488,
			"y": -1446.8154429374993,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 224.10568616156525,
			"height": 132.64767138771913,
			"seed": 23166016,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "hdcgYFfe"
				},
				{
					"id": "BFS599T3iYunztGIY3yLI",
					"type": "arrow"
				},
				{
					"id": "DRPTHvWzC0c3XTcq_5NF4",
					"type": "arrow"
				}
			],
			"updated": 1714421963746,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1030,
			"versionNonce": 1019127263,
			"isDeleted": false,
			"id": "hdcgYFfe",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -90.14026275666163,
			"y": -1392.9916072436397,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 89.23992919921875,
			"height": 25,
			"seed": 141030464,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714421963746,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Packager",
			"rawText": "Packager",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "TMgrKhbRxyfdZ7y6jRI3l",
			"originalText": "Packager",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 576,
			"versionNonce": 105966065,
			"isDeleted": false,
			"id": "EZPmA0tlymFo2z7mrk65n",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1774.5658984559532,
			"y": -2093.4235339349307,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 702.4069312848604,
			"height": 571.6994988740456,
			"seed": 1720992832,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "mAWdLX8H-mOQjRMf_fiGP",
					"type": "arrow"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 485,
			"versionNonce": 1929357727,
			"isDeleted": false,
			"id": "Mi0RxdSs",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1801.8505090686754,
			"y": -2057.46443822963,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 185.75985717773438,
			"height": 25,
			"seed": 996770880,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSL Class Layout",
			"rawText": "DiSL Class Layout",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "DiSL Class Layout",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 560,
			"versionNonce": 2103477201,
			"isDeleted": false,
			"id": "4i6mcveI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1814.4855869682424,
			"y": -1949.400743028199,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 503.47967529296875,
			"height": 350,
			"seed": 1161762752,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "package %s;\n\n%s\n\npublic class %s {\n\n  @After(marker = %s, scope = \"%s\")\n  public static void %s(DynamicContext di) {\n      int a = di.getLocalVariableValue(0, int.class);\n      int b = di.getLocalVariableValue(1, int.class);\n      System.out.println(\"disl: a=\" + a);\n      System.out.println(\"disl: b=\" + b);\n  }\n}",
			"rawText": "package %s;\n\n%s\n\npublic class %s {\n\n  @After(marker = %s, scope = \"%s\")\n  public static void %s(DynamicContext di) {\n      int a = di.getLocalVariableValue(0, int.class);\n      int b = di.getLocalVariableValue(1, int.class);\n      System.out.println(\"disl: a=\" + a);\n      System.out.println(\"disl: b=\" + b);\n  }\n}",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "package %s;\n\n%s\n\npublic class %s {\n\n  @After(marker = %s, scope = \"%s\")\n  public static void %s(DynamicContext di) {\n      int a = di.getLocalVariableValue(0, int.class);\n      int b = di.getLocalVariableValue(1, int.class);\n      System.out.println(\"disl: a=\" + a);\n      System.out.println(\"disl: b=\" + b);\n  }\n}",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 603,
			"versionNonce": 1773608383,
			"isDeleted": false,
			"id": "nPFHXBX1",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1917.972624101694,
			"y": -2630.38203134391,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 810.139404296875,
			"height": 100,
			"seed": 740614080,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Create a DiSL Metamodel and use it to enforce structure of the DiSLClass?\n    - The code can be generated using a M2T transformation\n    - The M2T interface can be shared in the instrumentation-common module\n    - The M2T logic for emitting Java could be injected to the common interface",
			"rawText": "Create a DiSL Metamodel and use it to enforce structure of the DiSLClass?\n    - The code can be generated using a M2T transformation\n    - The M2T interface can be shared in the instrumentation-common module\n    - The M2T logic for emitting Java could be injected to the common interface",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Create a DiSL Metamodel and use it to enforce structure of the DiSLClass?\n    - The code can be generated using a M2T transformation\n    - The M2T interface can be shared in the instrumentation-common module\n    - The M2T logic for emitting Java could be injected to the common interface",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 430,
			"versionNonce": 119660977,
			"isDeleted": false,
			"id": "IZAaMQf2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1911.8642611786274,
			"y": -2445.862111075477,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 661.3394775390625,
			"height": 50,
			"seed": 1740074048,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Create a Test Metamodel and use it for generating tests?\n    - Some sort of M2T could be reused from the instrumentation",
			"rawText": "Create a Test Metamodel and use it for generating tests?\n    - Some sort of M2T could be reused from the instrumentation",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Create a Test Metamodel and use it for generating tests?\n    - Some sort of M2T could be reused from the instrumentation",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 542,
			"versionNonce": 1571491295,
			"isDeleted": false,
			"id": "ru5ziorjltWB0P31L1fOc",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2920.5681703999953,
			"y": -2072.7322000660242,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 501.2913950025726,
			"height": 77.82345407099206,
			"seed": 104349632,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 408,
			"versionNonce": 1891402641,
			"isDeleted": false,
			"id": "tuxnNtqC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2933.6460919240462,
			"y": -2047.2798148994125,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 77.27993774414062,
			"height": 25,
			"seed": 1547420736,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "package",
			"rawText": "package",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "package",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 381,
			"versionNonce": 1777072639,
			"isDeleted": false,
			"id": "GY2QaHR99i8Rr5sVFm97H",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3031.60945378391,
			"y": -2062.5229249677873,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 229.61961549807074,
			"height": 58.204287830755675,
			"seed": 464385984,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "sqOYTWB9"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 366,
			"versionNonce": 1986521457,
			"isDeleted": false,
			"id": "sqOYTWB9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3063.8293033420273,
			"y": -2045.9207810524094,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 165.17991638183594,
			"height": 25,
			"seed": 1131187264,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "PACKAGE_NAME",
			"rawText": "PACKAGE_NAME",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "GY2QaHR99i8Rr5sVFm97H",
			"originalText": "PACKAGE_NAME",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 529,
			"versionNonce": 1937400351,
			"isDeleted": false,
			"id": "QW0XfKDH1xmZBHXX2U0SI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2918.1471789896755,
			"y": -1980.6523041240707,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 508.25288728432315,
			"height": 99.10990684852459,
			"seed": 1896536000,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "m69vfT37"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 452,
			"versionNonce": 1634440017,
			"isDeleted": false,
			"id": "m69vfT37",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3134.6436635253917,
			"y": -1943.5973506998084,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 75.25991821289062,
			"height": 25,
			"seed": 794197952,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Imports",
			"rawText": "Imports",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "QW0XfKDH1xmZBHXX2U0SI",
			"originalText": "Imports",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 674,
			"versionNonce": 1799800383,
			"isDeleted": false,
			"id": "iFF7aRUSr9i4uvaWPKGnI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2919.074464378911,
			"y": -1864.0518763130626,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 513.2090678129974,
			"height": 433.2669240181397,
			"seed": 279448640,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "mAWdLX8H-mOQjRMf_fiGP",
					"type": "arrow"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 382,
			"versionNonce": 1713307953,
			"isDeleted": false,
			"id": "cWmgZsIA",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2941.603388219854,
			"y": -1839.4582579108662,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 111.41989135742188,
			"height": 25,
			"seed": 324697152,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "public class",
			"rawText": "public class",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "public class",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 434,
			"versionNonce": 1345431135,
			"isDeleted": false,
			"id": "NT-eZNFkDh-etsKXngkKx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3070.2539581826068,
			"y": -1847.5617272729573,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 199.33895123578236,
			"height": 40.412284919062586,
			"seed": 194054080,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "tn3m2oJg"
				}
			],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 369,
			"versionNonce": 1105834769,
			"isDeleted": false,
			"id": "tn3m2oJg",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3103.113474388877,
			"y": -1839.855584813426,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 133.6199188232422,
			"height": 25,
			"seed": 1298096064,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "CLASS_NAME",
			"rawText": "CLASS_NAME",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "NT-eZNFkDh-etsKXngkKx",
			"originalText": "CLASS_NAME",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 977,
			"versionNonce": 1896579711,
			"isDeleted": false,
			"id": "f7cMJnJBBSGyPRL2h1bZi",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2940.8611511669937,
			"y": -1797.7852302951087,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 473.53678404199536,
			"height": 342.75729856935334,
			"seed": 579522496,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729553,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 365,
			"versionNonce": 423831793,
			"isDeleted": false,
			"id": "lj3DWIAe",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2956.4856335308414,
			"y": -1717.718933238329,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 179.579833984375,
			"height": 25,
			"seed": 791141440,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "public static void ",
			"rawText": "public static void ",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "public static void ",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 422,
			"versionNonce": 1855249055,
			"isDeleted": false,
			"id": "e0UCb_c2hpITEG83FuuMj",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3141.298636304702,
			"y": -1722.6933854002887,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 242.01691868223952,
			"height": 35,
			"seed": 1513377728,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "88Y1SQ7C"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 410,
			"versionNonce": 364045009,
			"isDeleted": false,
			"id": "88Y1SQ7C",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3185.0271502722867,
			"y": -1717.6933854002887,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 154.5598907470703,
			"height": 25,
			"seed": 1801430976,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "INST_METHOD",
			"rawText": "INST_METHOD",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "e0UCb_c2hpITEG83FuuMj",
			"originalText": "INST_METHOD",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 604,
			"versionNonce": 2105421503,
			"isDeleted": false,
			"id": "M-Kfp0mAI-PtHjGXDC-oJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2953.3931595972813,
			"y": -1783.9673076229976,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 431.05066874503564,
			"height": 50.96872104972341,
			"seed": 1896156224,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 474,
			"versionNonce": 1137729713,
			"isDeleted": false,
			"id": "T7So6aK-AQrFe6qhkUn_y",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2965.123548166531,
			"y": -1776.1835918434017,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 157.117774621462,
			"height": 37.3797179926305,
			"seed": 743827392,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "VJ9X4kRW"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 404,
			"versionNonce": 1751645919,
			"isDeleted": false,
			"id": "VJ9X4kRW",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2975.1924833898593,
			"y": -1769.9937328470864,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 136.9799041748047,
			"height": 25,
			"seed": 937721792,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "ANNOTATION",
			"rawText": "ANNOTATION",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "T7So6aK-AQrFe6qhkUn_y",
			"originalText": "ANNOTATION",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 528,
			"versionNonce": 826483345,
			"isDeleted": false,
			"id": "0_TY0Qd_IEeD1H4qxMYAt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3130.7467680823634,
			"y": -1775.772480094479,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 126.11538084439871,
			"height": 35,
			"seed": 1962934208,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "DWOwZ7KK"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 354,
			"versionNonce": 1373817599,
			"isDeleted": false,
			"id": "DWOwZ7KK",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3153.154472237473,
			"y": -1770.772480094479,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 81.29997253417969,
			"height": 25,
			"seed": 1553572928,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "MARKER",
			"rawText": "MARKER",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "0_TY0Qd_IEeD1H4qxMYAt",
			"originalText": "MARKER",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 420,
			"versionNonce": 1254271089,
			"isDeleted": false,
			"id": "CiAqFiize46EkTMNJ0KvP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3269.122414861289,
			"y": -1774.79951562203,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 101.6222564252721,
			"height": 35,
			"seed": 797968320,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "RqqC5xmK"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 351,
			"versionNonce": 913249055,
			"isDeleted": false,
			"id": "RqqC5xmK",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3286.803568708691,
			"y": -1769.79951562203,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 66.25994873046875,
			"height": 25,
			"seed": 1455316928,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "SCOPE",
			"rawText": "SCOPE",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "CiAqFiize46EkTMNJ0KvP",
			"originalText": "SCOPE",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 532,
			"versionNonce": 1866631711,
			"isDeleted": false,
			"id": "C7FWtG_wPb5Eyo_loT0Km",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2956.827814220139,
			"y": -1679.309369598731,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 431.0049896618225,
			"height": 205.7746011664788,
			"seed": 1392749632,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390771604,
			"link": null,
			"locked": false
		},
		{
			"type": "arrow",
			"version": 195,
			"versionNonce": 779966271,
			"isDeleted": false,
			"id": "mAWdLX8H-mOQjRMf_fiGP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2488.5653633915667,
			"y": -1789.5490542271652,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 403.1405744119743,
			"height": 5.53174828807073,
			"seed": 182706112,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "EZPmA0tlymFo2z7mrk65n",
				"gap": 11.592533650753012,
				"focus": 0.045130882370156396
			},
			"endBinding": {
				"elementId": "iFF7aRUSr9i4uvaWPKGnI",
				"gap": 27.36852657536997,
				"focus": 0.6027690507782721
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					403.1405744119743,
					5.53174828807073
				]
			]
		},
		{
			"type": "rectangle",
			"version": 621,
			"versionNonce": 68550705,
			"isDeleted": false,
			"id": "ZT9J3YGBx4mQD3AzNpnsk",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3478.207185482335,
			"y": -3121.019669772177,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1538.878451759293,
			"height": 854.9576026945601,
			"seed": 1803685824,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 311,
			"versionNonce": 1886263135,
			"isDeleted": false,
			"id": "vue9riTH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3551.79464691384,
			"y": -3079.4523759513095,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 209.53982543945312,
			"height": 25,
			"seed": 19443648,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSLClass Metamodel",
			"rawText": "DiSLClass Metamodel",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "DiSLClass Metamodel",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 762,
			"versionNonce": 648598033,
			"isDeleted": false,
			"id": "jEESRQkTwl9hCgtUhYZnb",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3917.3974756076977,
			"y": -2895.0391853917836,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 204.01398002812687,
			"height": 112.58660299607754,
			"seed": 275876928,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "LpLuHmZY"
				},
				{
					"id": "vq8Y8eFLQ_F3oEYZy5Xmd",
					"type": "arrow"
				},
				{
					"id": "tsXU5TkMrB8t_alVtEwFM",
					"type": "arrow"
				},
				{
					"id": "WsWvSbiMIcMJTr3wDS1R7",
					"type": "arrow"
				},
				{
					"id": "PZRYbqZcBgbCF94iieh69",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 684,
			"versionNonce": 311546751,
			"isDeleted": false,
			"id": "LpLuHmZY",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3970.744507736019,
			"y": -2851.2458838937446,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 97.31991577148438,
			"height": 25,
			"seed": 924520384,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSLClass",
			"rawText": "DiSLClass",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "jEESRQkTwl9hCgtUhYZnb",
			"originalText": "DiSLClass",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 483,
			"versionNonce": 1992714225,
			"isDeleted": false,
			"id": "4bepnK26_k71Na9DyXglB",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3542.5739474207885,
			"y": -2809.4794473236598,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 181.6207563719618,
			"height": 104.95952121530445,
			"seed": 891033536,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "z8WMWKAP"
				},
				{
					"id": "tsXU5TkMrB8t_alVtEwFM",
					"type": "arrow"
				},
				{
					"id": "Pbh_PW8bz-zbRqKbCZqle",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 417,
			"versionNonce": 220793759,
			"isDeleted": false,
			"id": "z8WMWKAP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3567.624437911457,
			"y": -2794.4996867160075,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 131.519775390625,
			"height": 75,
			"seed": 345243584,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Package\n----------------\n- name",
			"rawText": "Package\n----------------\n- name",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "4bepnK26_k71Na9DyXglB",
			"originalText": "Package\n----------------\n- name",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 780,
			"versionNonce": 2121679313,
			"isDeleted": false,
			"id": "HbCa_oKEGQ7qF-agkLbJk",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3543.8031933292186,
			"y": -2971.948360174455,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 186.13844283808703,
			"height": 103.80918438365197,
			"seed": 1724826688,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "vtJuV5nU"
				},
				{
					"id": "WsWvSbiMIcMJTr3wDS1R7",
					"type": "arrow"
				},
				{
					"id": "Pbh_PW8bz-zbRqKbCZqle",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 751,
			"versionNonce": 2086944703,
			"isDeleted": false,
			"id": "vtJuV5nU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3604.672448317598,
			"y": -2932.543767982629,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 64.39993286132812,
			"height": 25,
			"seed": 1738169408,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Import",
			"rawText": "Import",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "HbCa_oKEGQ7qF-agkLbJk",
			"originalText": "Import",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 528,
			"versionNonce": 449830833,
			"isDeleted": false,
			"id": "QPOJsoxpZ0wB2UR6VMy4S",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4292.12091126867,
			"y": -2891.052617925892,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 261.76786071544575,
			"height": 103.167784453276,
			"seed": 2145769408,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "BVcrhTQT"
				},
				{
					"id": "oz2Cl2niOnb_jwmKCw87D",
					"type": "arrow"
				},
				{
					"id": "f11yrabocYUCeMvSIvmiP",
					"type": "arrow"
				},
				{
					"id": "t6CLoeVZZoqfI9bF6jpbQ",
					"type": "arrow"
				},
				{
					"id": "G9ce6Yyu4eEXz9KviGv_t",
					"type": "arrow"
				},
				{
					"id": "vq8Y8eFLQ_F3oEYZy5Xmd",
					"type": "arrow"
				},
				{
					"id": "baHZT3szc3c3KDGEq01Q8",
					"type": "arrow"
				},
				{
					"id": "PZRYbqZcBgbCF94iieh69",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 504,
			"versionNonce": 576974815,
			"isDeleted": false,
			"id": "BVcrhTQT",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4305.034947217213,
			"y": -2876.968725699254,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 235.93978881835938,
			"height": 75,
			"seed": 399187904,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Instrumentation Method\n----------------\n- name",
			"rawText": "Instrumentation Method\n----------------\n- name",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "QPOJsoxpZ0wB2UR6VMy4S",
			"originalText": "Instrumentation Method\n----------------\n- name",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 659,
			"versionNonce": 435863953,
			"isDeleted": false,
			"id": "8bzyEUM5sCM-kVROQe4ti",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3899.055505892594,
			"y": -2661.0450366718915,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 211.73866614613144,
			"height": 114.37136801973202,
			"seed": 907308096,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "kdRVEOYg"
				},
				{
					"id": "G9ce6Yyu4eEXz9KviGv_t",
					"type": "arrow"
				},
				{
					"id": "t6CLoeVZZoqfI9bF6jpbQ",
					"type": "arrow"
				},
				{
					"id": "f11yrabocYUCeMvSIvmiP",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 641,
			"versionNonce": 691575807,
			"isDeleted": false,
			"id": "kdRVEOYg",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3924.304912513023,
			"y": -2641.3593526620252,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 161.23985290527344,
			"height": 75,
			"seed": 797519808,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Annotation\n----------------\n- activationTime",
			"rawText": "Annotation\n----------------\n- activationTime",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "8bzyEUM5sCM-kVROQe4ti",
			"originalText": "Annotation\n----------------\n- activationTime",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 814,
			"versionNonce": 422471537,
			"isDeleted": false,
			"id": "uO5NZ1wwxpfZMIb8Qk8u4",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3729.7059846978373,
			"y": -2426.2874755478656,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 220.1535543631271,
			"height": 112.28681824601063,
			"seed": 1539753024,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "nnWfe7qt"
				},
				{
					"id": "t6CLoeVZZoqfI9bF6jpbQ",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 782,
			"versionNonce": 618841119,
			"isDeleted": false,
			"id": "nnWfe7qt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3772.132821388678,
			"y": -2407.6440664248603,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 135.2998809814453,
			"height": 75,
			"seed": 2043832256,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Marker\n----------------\n- markerClass",
			"rawText": "Marker\n----------------\n- markerClass",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "uO5NZ1wwxpfZMIb8Qk8u4",
			"originalText": "Marker\n----------------\n- markerClass",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 778,
			"versionNonce": 465621951,
			"isDeleted": false,
			"id": "bmwa0g5R5uYZg-3r0r-_5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4012.5318894667002,
			"y": -2421.2421628737793,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 1102064704,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "j8kacGJQ"
				},
				{
					"id": "f11yrabocYUCeMvSIvmiP",
					"type": "arrow"
				}
			],
			"updated": 1714466329420,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 762,
			"versionNonce": 1461180447,
			"isDeleted": false,
			"id": "j8kacGJQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4020.8946375349533,
			"y": -2405.0423480507384,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 177.33981323242188,
			"height": 75,
			"seed": 328649792,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Scope\n----------------\n- methodIdentifier",
			"rawText": "Scope\n----------------\n- methodIdentifier",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "bmwa0g5R5uYZg-3r0r-_5",
			"originalText": "Scope\n----------------\n- methodIdentifier",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 784,
			"versionNonce": 199098161,
			"isDeleted": false,
			"id": "sBZBS_p1dws3gxZcKKKyX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4545.510881042347,
			"y": -2646.723397944817,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 193.7376376653664,
			"height": 104.25724900384098,
			"seed": 832580672,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "QjWwbdgk"
				},
				{
					"id": "oz2Cl2niOnb_jwmKCw87D",
					"type": "arrow"
				},
				{
					"id": "WVZGWrokCYtmyzJztjCZG",
					"type": "arrow"
				},
				{
					"id": "V2HGCvUrHJ9Fqg3MAaaMJ",
					"type": "arrow"
				},
				{
					"id": "_jtlDoe7-s0PXCYEeAq2r",
					"type": "arrow"
				}
			],
			"updated": 1714390729554,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 703,
			"versionNonce": 678004831,
			"isDeleted": false,
			"id": "QjWwbdgk",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4571.129768539581,
			"y": -2632.0947734428964,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 142.49986267089844,
			"height": 75,
			"seed": 695987264,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Exportable\n----------------\n- varIdentifier",
			"rawText": "Exportable\n----------------\n- varIdentifier",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "sBZBS_p1dws3gxZcKKKyX",
			"originalText": "Exportable\n----------------\n- varIdentifier",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1090,
			"versionNonce": 929668191,
			"isDeleted": false,
			"id": "oz2Cl2niOnb_jwmKCw87D",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4518.979032134632,
			"y": -2783.0952492099186,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 82.20549912845308,
			"height": 120.5690808582167,
			"seed": 512921536,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "Q3nC5vOK"
				}
			],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "QPOJsoxpZ0wB2UR6VMy4S",
				"gap": 4.78958426269719,
				"focus": -0.34650107929511426
			},
			"endBinding": {
				"elementId": "sBZBS_p1dws3gxZcKKKyX",
				"gap": 15.802770406884974,
				"focus": 0.038677380330492894
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					82.20549912845308,
					120.5690808582167
				]
			]
		},
		{
			"type": "text",
			"version": 23,
			"versionNonce": 2079794303,
			"isDeleted": false,
			"id": "Q3nC5vOK",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4352.267186929303,
			"y": -2552.438098608255,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 73.87991333007812,
			"height": 25,
			"seed": 405544896,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "exports",
			"rawText": "exports",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "oz2Cl2niOnb_jwmKCw87D",
			"originalText": "exports",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 2063,
			"versionNonce": 562049023,
			"isDeleted": false,
			"id": "f11yrabocYUCeMvSIvmiP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4045.061657040682,
			"y": -2539.933687019649,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 74.1495764437941,
			"height": 110.48128218702595,
			"seed": 1343667264,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "62VbvC6H"
				}
			],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "8bzyEUM5sCM-kVROQe4ti",
				"gap": 6.7399816325103075,
				"focus": 0.019181547721148825
			},
			"endBinding": {
				"elementId": "bmwa0g5R5uYZg-3r0r-_5",
				"gap": 8.210241958843653,
				"focus": 0.38473272563333993
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					74.1495764437941,
					110.48128218702595
				]
			]
		},
		{
			"type": "text",
			"version": 28,
			"versionNonce": 532429983,
			"isDeleted": false,
			"id": "62VbvC6H",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4172.399166012429,
			"y": -2556.13311994629,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 135.39987182617188,
			"height": 25,
			"seed": 1322120256,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "targetMethod",
			"rawText": "targetMethod",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "f11yrabocYUCeMvSIvmiP",
			"originalText": "targetMethod",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 2226,
			"versionNonce": 1636310943,
			"isDeleted": false,
			"id": "t6CLoeVZZoqfI9bF6jpbQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3941.7301714416576,
			"y": -2533.8333801191557,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 71.16282996021891,
			"height": 87.45587283166924,
			"seed": 813392960,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "CIMNlXFP"
				}
			],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "8bzyEUM5sCM-kVROQe4ti",
				"gap": 12.840288533003559,
				"focus": 0.04077750037536054
			},
			"endBinding": {
				"elementId": "uO5NZ1wwxpfZMIb8Qk8u4",
				"gap": 20.090031739620827,
				"focus": -0.20060623241385275
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-71.16282996021891,
					87.45587283166924
				]
			]
		},
		{
			"type": "text",
			"version": 22,
			"versionNonce": 392777919,
			"isDeleted": false,
			"id": "CIMNlXFP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4080.550318063415,
			"y": -2553.8170040419654,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 63.87994384765625,
			"height": 25,
			"seed": 1397014464,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "marker",
			"rawText": "marker",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "t6CLoeVZZoqfI9bF6jpbQ",
			"originalText": "marker",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1478,
			"versionNonce": 1164317407,
			"isDeleted": false,
			"id": "G9ce6Yyu4eEXz9KviGv_t",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4301.500478515263,
			"y": -2778.1174280111327,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 225.69375270593946,
			"height": 105.31606169302995,
			"seed": 392727616,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "c8RGK1OC"
				}
			],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "QPOJsoxpZ0wB2UR6VMy4S",
				"gap": 9.767405461483122,
				"focus": -0.041305335814183025
			},
			"endBinding": {
				"elementId": "8bzyEUM5sCM-kVROQe4ti",
				"gap": 11.756329646211498,
				"focus": -0.33649466761928887
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-225.69375270593946,
					105.31606169302995
				]
			]
		},
		{
			"type": "text",
			"version": 56,
			"versionNonce": 480174303,
			"isDeleted": false,
			"id": "c8RGK1OC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4073.5983612467735,
			"y": -2606.91483282881,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 103.83992004394531,
			"height": 25,
			"seed": 2099926976,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729554,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "annotation",
			"rawText": "annotation",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "G9ce6Yyu4eEXz9KviGv_t",
			"originalText": "annotation",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 402,
			"versionNonce": 702767249,
			"isDeleted": false,
			"id": "x4y9zd4k",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4620.020481625383,
			"y": -2671.5687178690837,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 10.319992065429688,
			"height": 25,
			"seed": 536396864,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "oz2Cl2niOnb_jwmKCw87D",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "*",
			"rawText": "*",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "*",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 821,
			"versionNonce": 2127131775,
			"isDeleted": false,
			"id": "vq8Y8eFLQ_F3oEYZy5Xmd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4129.385140464858,
			"y": -2836.7341584949263,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 143.67123990431446,
			"height": 1.521599006703127,
			"seed": 399496256,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "ghpVqDv7"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "jEESRQkTwl9hCgtUhYZnb",
				"gap": 7.973684829032891,
				"focus": 0.01476179189992905
			},
			"endBinding": {
				"elementId": "DyOg4Lqf",
				"gap": 6.467355409986794,
				"focus": -1.5025936831977966
			},
			"lastCommittedPoint": null,
			"startArrowhead": "diamond",
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					143.67123990431446,
					1.521599006703127
				]
			]
		},
		{
			"type": "text",
			"version": 21,
			"versionNonce": 11216497,
			"isDeleted": false,
			"id": "ghpVqDv7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3987.634123543864,
			"y": -2719.206440754262,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 40.77995300292969,
			"height": 25,
			"seed": 1587665984,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "logic",
			"rawText": "logic",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "vq8Y8eFLQ_F3oEYZy5Xmd",
			"originalText": "logic",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 254,
			"versionNonce": 265647391,
			"isDeleted": false,
			"id": "DyOg4Lqf",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4274.54314693474,
			"y": -2866.5067005081396,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 10.319992065429688,
			"height": 25,
			"seed": 205345856,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "vq8Y8eFLQ_F3oEYZy5Xmd",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "*",
			"rawText": "*",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "*",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 945,
			"versionNonce": 783305055,
			"isDeleted": false,
			"id": "tsXU5TkMrB8t_alVtEwFM",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3911.382280406312,
			"y": -2831.760892616219,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 176.74579812266575,
			"height": 84.3811245133752,
			"seed": 1531185088,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "xrkK4CWm"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "jEESRQkTwl9hCgtUhYZnb",
				"gap": 6.015195201386405,
				"focus": 0.4246611138225681
			},
			"endBinding": {
				"elementId": "4bepnK26_k71Na9DyXglB",
				"gap": 10.441778490896013,
				"focus": 0.6047877194062725
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-176.74579812266575,
					84.3811245133752
				]
			]
		},
		{
			"type": "text",
			"version": 20,
			"versionNonce": 281508159,
			"isDeleted": false,
			"id": "xrkK4CWm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3823.7653892508615,
			"y": -2798.4890173939034,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 77.27993774414062,
			"height": 25,
			"seed": 1206192064,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "package",
			"rawText": "package",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "tsXU5TkMrB8t_alVtEwFM",
			"originalText": "package",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 848,
			"versionNonce": 1555711,
			"isDeleted": false,
			"id": "WsWvSbiMIcMJTr3wDS1R7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3898.393886968821,
			"y": -2841.3139628649624,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 155.96528221655944,
			"height": 73.78898348147004,
			"seed": 1984652352,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "0gbicnUv"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "jEESRQkTwl9hCgtUhYZnb",
				"gap": 19.003588638876636,
				"focus": -0.5230161783082307
			},
			"endBinding": {
				"elementId": "VeWtKuD9",
				"gap": 4.1189000814752035,
				"focus": 1.2012376330247057
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-155.96528221655944,
					-73.78898348147004
				]
			]
		},
		{
			"type": "text",
			"version": 19,
			"versionNonce": 153352543,
			"isDeleted": false,
			"id": "0gbicnUv",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3822.2024507535566,
			"y": -2898.5165382444275,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 68.73991394042969,
			"height": 25,
			"seed": 784424000,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "imports",
			"rawText": "imports",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "WsWvSbiMIcMJTr3wDS1R7",
			"originalText": "imports",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1312,
			"versionNonce": 409341247,
			"isDeleted": false,
			"id": "PZRYbqZcBgbCF94iieh69",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4321.598493297404,
			"y": -3266.3790819117203,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 291.407247942122,
			"height": 349.61445752860936,
			"seed": 181557184,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "XSAo1CS7YiG0eYFgcGnnJ",
				"gap": 8.613498447226902,
				"focus": -0.03048783779355297
			},
			"endBinding": {
				"elementId": "jEESRQkTwl9hCgtUhYZnb",
				"gap": 21.725438991327565,
				"focus": -0.11694128780078179
			},
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": null,
			"points": [
				[
					0,
					0
				],
				[
					-247.46813823528862,
					211.4353571962841
				],
				[
					-291.407247942122,
					349.61445752860936
				]
			]
		},
		{
			"type": "rectangle",
			"version": 678,
			"versionNonce": 507213183,
			"isDeleted": false,
			"id": "XSAo1CS7YiG0eYFgcGnnJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4330.211991744631,
			"y": -3464.8880672886226,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 191.4683882881172,
			"height": 207.39918854404885,
			"seed": 268348480,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "PZRYbqZcBgbCF94iieh69",
					"type": "arrow"
				},
				{
					"type": "text",
					"id": "Vp3oErAP"
				},
				{
					"id": "baHZT3szc3c3KDGEq01Q8",
					"type": "arrow"
				},
				{
					"id": "QhMSvOiqMmT3Rqk-pgcmI",
					"type": "arrow"
				},
				{
					"id": "Ka1cfNX-fwAaQviq-81sC",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 615,
			"versionNonce": 226485745,
			"isDeleted": false,
			"id": "Vp3oErAP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4360.186298193376,
			"y": -3398.688473016598,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 131.519775390625,
			"height": 75,
			"seed": 1881312320,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Metaclass\n----------------\n+ visit()",
			"rawText": "Metaclass\n----------------\n+ visit()",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "XSAo1CS7YiG0eYFgcGnnJ",
			"originalText": "Metaclass\n----------------\n+ visit()",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1114,
			"versionNonce": 1447364991,
			"isDeleted": false,
			"id": "baHZT3szc3c3KDGEq01Q8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4413.616109391536,
			"y": -2900.7918600578087,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 1.9788401715786677,
			"height": 352.0539255645431,
			"seed": 1218298944,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "QPOJsoxpZ0wB2UR6VMy4S",
				"gap": 9.739242131916853,
				"focus": -0.0689469945288724
			},
			"endBinding": {
				"elementId": "XSAo1CS7YiG0eYFgcGnnJ",
				"gap": 4.6430931222223535,
				"focus": 0.1548831736933379
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-1.9788401715786677,
					-352.0539255645431
				]
			]
		},
		{
			"type": "arrow",
			"version": 1030,
			"versionNonce": 554008991,
			"isDeleted": false,
			"id": "QhMSvOiqMmT3Rqk-pgcmI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4492.490397823294,
			"y": -3252.686967273377,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 165.235967146863,
			"height": 513.6929507476611,
			"seed": 712368064,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "XSAo1CS7YiG0eYFgcGnnJ",
				"gap": 4.801911471197172,
				"focus": 0.05519350287985653
			},
			"endBinding": null,
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": null,
			"points": [
				[
					0,
					0
				],
				[
					165.235967146863,
					236.4340948327972
				],
				[
					93.71250338786194,
					513.6929507476611
				]
			]
		},
		{
			"type": "arrow",
			"version": 801,
			"versionNonce": 155317695,
			"isDeleted": false,
			"id": "Ka1cfNX-fwAaQviq-81sC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4320.8194441352625,
			"y": -3346.899215474765,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 473.8278538203517,
			"height": 429.25658165688947,
			"seed": 1286932416,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "XSAo1CS7YiG0eYFgcGnnJ",
				"gap": 9.392547609368648,
				"focus": 0.3792563619042085
			},
			"endBinding": null,
			"lastCommittedPoint": null,
			"startArrowhead": "arrow",
			"endArrowhead": null,
			"points": [
				[
					0,
					0
				],
				[
					-384.88248552055484,
					299.8683904576301
				],
				[
					-473.8278538203517,
					429.25658165688947
				]
			]
		},
		{
			"type": "arrow",
			"version": 900,
			"versionNonce": 2096964063,
			"isDeleted": false,
			"id": "Pbh_PW8bz-zbRqKbCZqle",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3643.6009535914995,
			"y": -2862.4499509384027,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.5199019032502292,
			"height": 51.854671239692834,
			"seed": 512988224,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329420,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "HbCa_oKEGQ7qF-agkLbJk",
				"gap": 5.689224852400457,
				"focus": -0.08897988699866327
			},
			"endBinding": {
				"elementId": "4bepnK26_k71Na9DyXglB",
				"gap": 1.115832375050104,
				"focus": 0.07716193009835362
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					-1.5199019032502292,
					51.854671239692834
				]
			]
		},
		{
			"type": "rectangle",
			"version": 867,
			"versionNonce": 1746457055,
			"isDeleted": false,
			"id": "lBamrLyYu4-QeP4IsmZvI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3598.0326959144836,
			"y": -3464.981028846955,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 257.8881015044217,
			"height": 212.03862339834996,
			"seed": 470457408,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "xyOy3KHx"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 782,
			"versionNonce": 1610416017,
			"isDeleted": false,
			"id": "xyOy3KHx",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3661.216858971382,
			"y": -3408.96171714778,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 131.519775390625,
			"height": 100,
			"seed": 1236580288,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Model\n----------------\n- dislClass\n+ visit()",
			"rawText": "Model\n----------------\n- dislClass\n+ visit()",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "lBamrLyYu4-QeP4IsmZvI",
			"originalText": "Model\n----------------\n- dislClass\n+ visit()",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 115,
			"versionNonce": 1778466303,
			"isDeleted": false,
			"id": "VeWtKuD9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3740.0778750042905,
			"y": -2944.2218464279076,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 10.319992065429688,
			"height": 25,
			"seed": 1839541312,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "WsWvSbiMIcMJTr3wDS1R7",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "*",
			"rawText": "*",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "*",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 721,
			"versionNonce": 540599665,
			"isDeleted": false,
			"id": "Qr3juiL7NoU5RDlWmK5dL",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4341.335166895758,
			"y": -2416.2920473616273,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 2012627135,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "20lG8NBQ"
				},
				{
					"id": "WVZGWrokCYtmyzJztjCZG",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 726,
			"versionNonce": 1212622367,
			"isDeleted": false,
			"id": "20lG8NBQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4394.69786155825,
			"y": -2375.0922325385864,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 87.33992004394531,
			"height": 25,
			"seed": 1041092831,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Argument",
			"rawText": "Argument",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "Qr3juiL7NoU5RDlWmK5dL",
			"originalText": "Argument",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 715,
			"versionNonce": 532483921,
			"isDeleted": false,
			"id": "hQCRs_SGsYqCC7NnAGUnd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4557.487622199196,
			"y": -2416.7550234635814,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 128514001,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "syBjkXHE"
				},
				{
					"id": "V2HGCvUrHJ9Fqg3MAaaMJ",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 715,
			"versionNonce": 299567679,
			"isDeleted": false,
			"id": "syBjkXHE",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4632.800298551141,
			"y": -2375.5552086405405,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 43.43995666503906,
			"height": 25,
			"seed": 406574513,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Field",
			"rawText": "Field",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "hQCRs_SGsYqCC7NnAGUnd",
			"originalText": "Field",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 697,
			"versionNonce": 1424031025,
			"isDeleted": false,
			"id": "XFcG5fd59_F8dzIypMrHE",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4770.566098856758,
			"y": -2417.298191154128,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 1230955551,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "exwRPmNo"
				},
				{
					"id": "_jtlDoe7-s0PXCYEeAq2r",
					"type": "arrow"
				}
			],
			"updated": 1714390729555,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 694,
			"versionNonce": 597518943,
			"isDeleted": false,
			"id": "exwRPmNo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4829.418783753625,
			"y": -2376.098376331087,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 76.35993957519531,
			"height": 25,
			"seed": 1430980671,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Variable",
			"rawText": "Variable",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "XFcG5fd59_F8dzIypMrHE",
			"originalText": "Variable",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 551,
			"versionNonce": 379638271,
			"isDeleted": false,
			"id": "WVZGWrokCYtmyzJztjCZG",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4508.034359496423,
			"y": -2430.4328089157684,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 87.56668803668708,
			"height": 104.64592631896312,
			"seed": 389341297,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329421,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "Qr3juiL7NoU5RDlWmK5dL",
				"gap": 14.140761554141136,
				"focus": 0.09085283043696232
			},
			"endBinding": {
				"elementId": "sBZBS_p1dws3gxZcKKKyX",
				"gap": 7.387413706244388,
				"focus": -0.021523214504359938
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "triangle_outline",
			"points": [
				[
					0,
					0
				],
				[
					87.56668803668708,
					-104.64592631896312
				]
			]
		},
		{
			"type": "arrow",
			"version": 414,
			"versionNonce": 1655021119,
			"isDeleted": false,
			"id": "V2HGCvUrHJ9Fqg3MAaaMJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4656.975288229313,
			"y": -2433.9633708113142,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.5486130435874657,
			"height": 100.9889495116995,
			"seed": 1402520831,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329421,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "hQCRs_SGsYqCC7NnAGUnd",
				"gap": 17.208347347732797,
				"focus": 0.03619958966411162
			},
			"endBinding": {
				"elementId": "sBZBS_p1dws3gxZcKKKyX",
				"gap": 7.51382861796219,
				"focus": -0.12422048588843537
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "triangle_outline",
			"points": [
				[
					0,
					0
				],
				[
					-1.5486130435874657,
					-100.9889495116995
				]
			]
		},
		{
			"type": "arrow",
			"version": 392,
			"versionNonce": 278157951,
			"isDeleted": false,
			"id": "_jtlDoe7-s0PXCYEeAq2r",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 4836.850713811538,
			"y": -2430.857413857492,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 133.75444136263468,
			"height": 106.58835919364128,
			"seed": 1842609297,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329421,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "XFcG5fd59_F8dzIypMrHE",
				"gap": 13.559222703363957,
				"focus": 0.32632046464092346
			},
			"endBinding": {
				"elementId": "sBZBS_p1dws3gxZcKKKyX",
				"gap": 5.020375889842853,
				"focus": 0.06776997729987289
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "triangle_outline",
			"points": [
				[
					0,
					0
				],
				[
					-133.75444136263468,
					-106.58835919364128
				]
			]
		},
		{
			"type": "arrow",
			"version": 2536,
			"versionNonce": 2073236511,
			"isDeleted": false,
			"id": "BFS599T3iYunztGIY3yLI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -60.438992398142005,
			"y": -1558.082412596037,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.462826617285316,
			"height": 107.30641823795827,
			"seed": 1945291423,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "BYglDsra"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "gDtu4ugrTFIU2rFa6Kwv6",
				"gap": 11.851154967579987,
				"focus": 0.08883498178739785
			},
			"endBinding": {
				"elementId": "TMgrKhbRxyfdZ7y6jRI3l",
				"gap": 3.960551420579577,
				"focus": -0.11064153401646845
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					1.462826617285316,
					107.30641823795827
				]
			]
		},
		{
			"type": "text",
			"version": 22,
			"versionNonce": 291921617,
			"isDeleted": false,
			"id": "BYglDsra",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -120.33753056655013,
			"y": -1021.1003635412459,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 121.25990295410156,
			"height": 25,
			"seed": 46676927,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Object Path",
			"rawText": "Object Path",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "BFS599T3iYunztGIY3yLI",
			"originalText": "Object Path",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 254,
			"versionNonce": 481902175,
			"isDeleted": false,
			"id": "cNY4tPV3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -183.9691747552348,
			"y": -2118.790069985399,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 156.1998748779297,
			"height": 25,
			"seed": 276910623,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714421963746,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Code Generator",
			"rawText": "Code Generator",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "Code Generator",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1203,
			"versionNonce": 1668501361,
			"isDeleted": false,
			"id": "n6XuORC8sbkgD7HUp220b",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -458.7729711191552,
			"y": -2039.468884140786,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 245.06280671474073,
			"height": 0.695150676621779,
			"seed": 666001745,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "SbkwDMqU"
				}
			],
			"updated": 1714421963833,
			"link": null,
			"locked": false,
			"startBinding": null,
			"endBinding": {
				"elementId": "wl8CMxzKm3eTel8-SUckl",
				"focus": 0.336910231294277,
				"gap": 16.304059898691065
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					245.06280671474073,
					-0.695150676621779
				]
			]
		},
		{
			"type": "text",
			"version": 12,
			"versionNonce": 366068447,
			"isDeleted": false,
			"id": "SbkwDMqU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -408.50359645572155,
			"y": -1379.7054214810607,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 53.979949951171875,
			"height": 25,
			"seed": 970350769,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Model",
			"rawText": "Model",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "n6XuORC8sbkgD7HUp220b",
			"originalText": "Model",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 460,
			"versionNonce": 1075212959,
			"isDeleted": false,
			"id": "jIgmsntPogyKY0Y3oI2zv",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -168.42629941737914,
			"y": -2078.820291015896,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 210.2905233630223,
			"height": 90.31147319314641,
			"seed": 1892311025,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "Edj2Ig7N"
				},
				{
					"id": "OVvaVFHBgXxv59sCbSTf5",
					"type": "arrow"
				}
			],
			"updated": 1714421963746,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 428,
			"versionNonce": 910760895,
			"isDeleted": false,
			"id": "Edj2Ig7N",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -142.86096327297736,
			"y": -2046.164554419323,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 159.15985107421875,
			"height": 25,
			"seed": 208400177,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714424221070,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Model::transform",
			"rawText": "Model::transform",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "jIgmsntPogyKY0Y3oI2zv",
			"originalText": "Model::transform",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 591,
			"versionNonce": 1911046111,
			"isDeleted": false,
			"id": "uUN3CXbm2ik0V_-z6NHp-",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -61.14832499096873,
			"y": -1826.5133013066827,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 2.690974797231128,
			"height": 114.29334889187339,
			"seed": 751389727,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "GmewwOPS"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "wl8CMxzKm3eTel8-SUckl",
				"gap": 7.08109803817365,
				"focus": -0.014278973624994227
			},
			"endBinding": {
				"elementId": "gDtu4ugrTFIU2rFa6Kwv6",
				"gap": 9.638713463473096,
				"focus": -0.04575733747176878
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					2.690974797231128,
					114.29334889187339
				]
			]
		},
		{
			"type": "text",
			"version": 32,
			"versionNonce": 523753247,
			"isDeleted": false,
			"id": "GmewwOPS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -120.4356556747551,
			"y": -1276.5980974851384,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 121.91989135742188,
			"height": 25,
			"seed": 1438687633,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729555,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Source Path",
			"rawText": "Source Path",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "uUN3CXbm2ik0V_-z6NHp-",
			"originalText": "Source Path",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 617,
			"versionNonce": 1315220255,
			"isDeleted": false,
			"id": "cREc68AEtObVDomNIYB0Y",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -169.42118355641634,
			"y": -1942.5825076182382,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 210.2905233630223,
			"height": 90.31147319314641,
			"seed": 1314238961,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "OVvaVFHBgXxv59sCbSTf5",
					"type": "arrow"
				},
				{
					"type": "text",
					"id": "APt98mUw"
				}
			],
			"updated": 1714421963746,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 50,
			"versionNonce": 1677717311,
			"isDeleted": false,
			"id": "APt98mUw",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -118.42586999502238,
			"y": -1909.926771021665,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 108.29989624023438,
			"height": 25,
			"seed": 860976145,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714421963746,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "TextWriter",
			"rawText": "TextWriter",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "cREc68AEtObVDomNIYB0Y",
			"originalText": "TextWriter",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 351,
			"versionNonce": 1514119935,
			"isDeleted": false,
			"id": "OVvaVFHBgXxv59sCbSTf5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -65.4827470502717,
			"y": -1985.611297756268,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.1830295996806797,
			"height": 41.95271266376767,
			"seed": 396640017,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1714466329421,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "jIgmsntPogyKY0Y3oI2zv",
				"gap": 2.897520066481775,
				"focus": 0.03342243207709898
			},
			"endBinding": {
				"elementId": "cREc68AEtObVDomNIYB0Y",
				"gap": 1.0760774742620924,
				"focus": 0.012027039962580007
			},
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					1.1830295996806797,
					41.95271266376767
				]
			]
		},
		{
			"type": "arrow",
			"version": 338,
			"versionNonce": 293156927,
			"isDeleted": false,
			"id": "DRPTHvWzC0c3XTcq_5NF4",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 71.45210630336236,
			"y": -1377.3032188581817,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 186.88453254508704,
			"height": 1.5058122607464384,
			"seed": 1703667295,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "zFnuQkS3"
				}
			],
			"updated": 1714466329419,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "TMgrKhbRxyfdZ7y6jRI3l",
				"gap": 4.919561379631986,
				"focus": 0.06144712793168621
			},
			"endBinding": null,
			"lastCommittedPoint": null,
			"startArrowhead": null,
			"endArrowhead": "arrow",
			"points": [
				[
					0,
					0
				],
				[
					186.88453254508704,
					-1.5058122607464384
				]
			]
		},
		{
			"type": "text",
			"version": 8,
			"versionNonce": 2089766417,
			"isDeleted": false,
			"id": "zFnuQkS3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 162.62887817922308,
			"y": -887.3840876054597,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 39.3599853515625,
			"height": 25,
			"seed": 1910831519,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729556,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "JAR",
			"rawText": "JAR",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "DRPTHvWzC0c3XTcq_5NF4",
			"originalText": "JAR",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 27,
			"versionNonce": 1365317503,
			"isDeleted": false,
			"id": "mCPqZr05",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2981.9391831942553,
			"y": -1662.7263478244868,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 134.59991455078125,
			"height": 25,
			"seed": 1076615487,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390729556,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "INST_LOGIC",
			"rawText": "INST_LOGIC",
			"textAlign": "left",
			"verticalAlign": "top",
			"containerId": null,
			"originalText": "INST_LOGIC",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1116,
			"versionNonce": 1299245855,
			"isDeleted": false,
			"id": "GGOi2bpgkVzUtTeywsywZ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2963.548798322554,
			"y": -1627.7745716908123,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 408.01575642275156,
			"height": 38.68629691316938,
			"seed": 813415633,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "lTiIcOPi"
				}
			],
			"updated": 1714466329421,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1207,
			"versionNonce": 983776401,
			"isDeleted": false,
			"id": "lTiIcOPi",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3104.2267052204534,
			"y": -1620.9314232342276,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 126.65994262695312,
			"height": 25,
			"seed": 74316465,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390762486,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "ARGUMENTS",
			"rawText": "ARGUMENTS",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "GGOi2bpgkVzUtTeywsywZ",
			"originalText": "ARGUMENTS",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1110,
			"versionNonce": 256151793,
			"isDeleted": false,
			"id": "PjL9EeQBk9hWmCvceMBXr",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2964.8082329481786,
			"y": -1578.2436542423495,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 408.8748329138988,
			"height": 38.68629691316938,
			"seed": 1028200159,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "jl4SWRgt"
				}
			],
			"updated": 1714390758887,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1133,
			"versionNonce": 390548177,
			"isDeleted": false,
			"id": "jl4SWRgt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3112.6456738191905,
			"y": -1571.4005057857648,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 113.199951171875,
			"height": 25,
			"seed": 450945791,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390758887,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "VARIABLES",
			"rawText": "VARIABLES",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "PjL9EeQBk9hWmCvceMBXr",
			"originalText": "VARIABLES",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1224,
			"versionNonce": 1737483153,
			"isDeleted": false,
			"id": "hF3iDRfomgd-JApYh_1Sh",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 2967.6396908984198,
			"y": -1530.2840625140195,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 406.66796377612525,
			"height": 37.09592659842293,
			"seed": 1718961343,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "9kzMz2fJ"
				}
			],
			"updated": 1714390760818,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1240,
			"versionNonce": 2077182321,
			"isDeleted": false,
			"id": "9kzMz2fJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 3133.173692622908,
			"y": -1524.236099214808,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 75.59996032714844,
			"height": 25,
			"seed": 235689183,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1714390760818,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "FIELDS",
			"rawText": "FIELDS",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "hF3iDRfomgd-JApYh_1Sh",
			"originalText": "FIELDS",
			"lineHeight": 1.25
		}
	],
	"appState": {
		"theme": "light",
		"viewBackgroundColor": "#ffffff",
		"currentItemStrokeColor": "#2f9e44",
		"currentItemBackgroundColor": "transparent",
		"currentItemFillStyle": "solid",
		"currentItemStrokeWidth": 2,
		"currentItemStrokeStyle": "solid",
		"currentItemRoughness": 1,
		"currentItemOpacity": 100,
		"currentItemFontFamily": 1,
		"currentItemFontSize": 20,
		"currentItemTextAlign": "left",
		"currentItemStartArrowhead": null,
		"currentItemEndArrowhead": "arrow",
		"scrollX": 990.7714406055879,
		"scrollY": 601.407057680885,
		"zoom": {
			"value": 1.3546369521586357
		},
		"currentItemRoundness": "round",
		"gridSize": null,
		"gridColor": {
			"Bold": "#C9C9C9FF",
			"Regular": "#EDEDEDFF"
		},
		"currentStrokeOptions": null,
		"previousGridSize": null,
		"frameRendering": {
			"enabled": true,
			"clip": true,
			"name": true,
			"outline": true
		}
	},
	"files": {}
}
```
%%