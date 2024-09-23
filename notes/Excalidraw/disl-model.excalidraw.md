---

excalidraw-plugin: parsed
tags: [excalidraw]

---
==⚠  Switch to EXCALIDRAW VIEW in the MORE OPTIONS menu of this document. ⚠==


# Text Elements
DiSL Instrumentation Generating Workflow ^ktMjMaWn

Compiler ^O0QFPg3V

Packager ^pU9leOse

Object Path ^omf7PqHy

Code Generator ^8cUKJT9z

Model ^bJKzixZO

Model::transform ^30bpV1j8

Source Path ^cXheyDWH

TextWriter ^E94ACQrt

JAR ^5gzNiyV8

Create a DiSL Metamodel and use it to enforce structure of the DiSLClass?
    - The code can be generated using a M2T transformation
    - The M2T interface can be shared in the instrumentation-common module
    - The M2T logic for emitting Java could be injected to the common interface ^Lcd8Sjm8

Create a Test Metamodel and use it for generating tests?
    - Some sort of M2T could be reused from the instrumentation ^bCKopXby

DiSLClass Metamodel ^eGA4HeB4

DiSLClass ^XnAlE70p

Package
----------------
- name ^qLoTDm4L

Import ^qyRNEAm9

Instrumentation Method
----------------
- name ^7h5KDroU

Annotation
----------------
- activationTime ^oBdnkh2N

Marker
----------------
- markerClass ^bHAl32JU

Scope
----------------
- methodIdentifier ^K8J6EW1f

Exportable
----------------
- varIdentifier ^MBdKRmYS

exports ^k2N3E6NL

targetMethod ^InvDj0Jn

marker ^kZV7HJ63

annotation ^DimuyQsQ

* ^lfIjSvUb

logic ^b9rOm2t0

* ^I2NAW9hE

package ^mzgZrip5

imports ^xUlhX6dD

Metaclass
----------------
+ visit() ^PGMcL9QY

Model
----------------
- dislClass
+ visit() ^H7fy2xLa

* ^g7hfsbPh

Argument ^RAUFJIgC

Field ^YXjV3ieV

Variable ^WqyLX9QA

DiSL Class Layout ^JjpNZSnW

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
} ^N1TyDhs9

package ^pzQFrZ9l

PACKAGE_NAME ^unPkX3IS

Imports ^6sa9bsE8

public class ^FEnNVw67

CLASS_NAME ^CyxhAhUr

public static void  ^14T2GbeW

INST_METHOD ^ngq5TkdC

ANNOTATION ^lUbAUeQj

MARKER ^Bb4Ac1DY

SCOPE ^qxrWXPlz

INST_LOGIC ^wK0A1Qxj

ARGUMENTS ^qHGqlOKQ

VARIABLES ^dScp8SY6

FIELDS ^KFg8fMyF

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
			"version": 565,
			"versionNonce": 1879018469,
			"isDeleted": false,
			"id": "QrB3nCiVArohjSd0f1XJy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -509.9136627629194,
			"y": -630.5497594326403,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 494.5949124956406,
			"height": 954.583893865281,
			"seed": 1018548811,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1727006084444,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 208,
			"versionNonce": 978241349,
			"isDeleted": false,
			"id": "ktMjMaWn",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -477.8100093370695,
			"y": -597.7492846755144,
			"strokeColor": "#1971c2",
			"backgroundColor": "transparent",
			"width": 416.71966552734375,
			"height": 25,
			"seed": 981160171,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084444,
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
			"version": 838,
			"versionNonce": 568301221,
			"isDeleted": false,
			"id": "-8qdJNXydQeIpWgJ6cj1G",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -405.161962761433,
			"y": -534.1765642298619,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 261.06423738556737,
			"height": 309.4619243282191,
			"seed": 1817705355,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "5Mt8pdQPqk-b0kYzmr2-6",
					"type": "arrow"
				},
				{
					"id": "Do94x1moIkznu_jRQxol0",
					"type": "arrow"
				}
			],
			"updated": 1727006084444,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 877,
			"versionNonce": 828340741,
			"isDeleted": false,
			"id": "RfC5eXQI6G8AOcX6lLcXI",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -371.2788620625943,
			"y": -93.7014795081227,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 224.10568616156525,
			"height": 132.64767138771913,
			"seed": 512119339,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "O0QFPg3V"
				},
				{
					"id": "oR00WqcyFHNqKULRA3ein",
					"type": "arrow"
				},
				{
					"id": "Do94x1moIkznu_jRQxol0",
					"type": "arrow"
				}
			],
			"updated": 1727006084444,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 833,
			"versionNonce": 1006490309,
			"isDeleted": false,
			"id": "O0QFPg3V",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -297.0559750364992,
			"y": -39.87764381426314,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 75.659912109375,
			"height": 25,
			"seed": 837866699,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084445,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Compiler",
			"rawText": "Compiler",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "RfC5eXQI6G8AOcX6lLcXI",
			"originalText": "Compiler",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1074,
			"versionNonce": 812809413,
			"isDeleted": false,
			"id": "6jV65uDaRXHqh7u4F9LF3",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -365.3289994935445,
			"y": 162.06431650571403,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 224.10568616156525,
			"height": 132.64767138771913,
			"seed": 103940971,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "pU9leOse"
				},
				{
					"id": "oR00WqcyFHNqKULRA3ein",
					"type": "arrow"
				},
				{
					"id": "7vp8C1qyiKu3LUzC1XIWu",
					"type": "arrow"
				}
			],
			"updated": 1727006084444,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1045,
			"versionNonce": 1425054757,
			"isDeleted": false,
			"id": "pU9leOse",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -297.8961210123712,
			"y": 215.8881521995736,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 89.23992919921875,
			"height": 25,
			"seed": 596925963,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084444,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Packager",
			"rawText": "Packager",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "6jV65uDaRXHqh7u4F9LF3",
			"originalText": "Packager",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 2595,
			"versionNonce": 36412037,
			"isDeleted": false,
			"id": "oR00WqcyFHNqKULRA3ein",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -268.19485065385163,
			"y": 50.797346847176414,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.462826617285316,
			"height": 107.30641823795804,
			"seed": 621962411,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "omf7PqHy"
				}
			],
			"updated": 1727006144441,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "RfC5eXQI6G8AOcX6lLcXI",
				"gap": 11.851154967579987,
				"focus": 0.08883498178739785
			},
			"endBinding": {
				"elementId": "6jV65uDaRXHqh7u4F9LF3",
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
					107.30641823795804
				]
			]
		},
		{
			"type": "text",
			"version": 38,
			"versionNonce": 1084305957,
			"isDeleted": false,
			"id": "omf7PqHy",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -328.0933888222597,
			"y": 91.9505559661551,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 121.25990295410156,
			"height": 25,
			"seed": 1649841995,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084445,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Object Path",
			"rawText": "Object Path",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "oR00WqcyFHNqKULRA3ein",
			"originalText": "Object Path",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 269,
			"versionNonce": 1246720581,
			"isDeleted": false,
			"id": "8cUKJT9z",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -391.7250330109444,
			"y": -509.9103105421859,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 156.1998748779297,
			"height": 25,
			"seed": 1505457643,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084444,
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
			"version": 1218,
			"versionNonce": 1653793189,
			"isDeleted": false,
			"id": "5Mt8pdQPqk-b0kYzmr2-6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -666.5288293748649,
			"y": -430.58912469757297,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 245.06280671474073,
			"height": 0.695150676621779,
			"seed": 1514897547,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "bJKzixZO"
				}
			],
			"updated": 1727006084444,
			"link": null,
			"locked": false,
			"startBinding": null,
			"endBinding": {
				"elementId": "-8qdJNXydQeIpWgJ6cj1G",
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
			"version": 28,
			"versionNonce": 1281467781,
			"isDeleted": false,
			"id": "bJKzixZO",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -570.9874009930804,
			"y": -443.43670003588386,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 53.979949951171875,
			"height": 25,
			"seed": 1841379115,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084445,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Model",
			"rawText": "Model",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "5Mt8pdQPqk-b0kYzmr2-6",
			"originalText": "Model",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 475,
			"versionNonce": 543718501,
			"isDeleted": false,
			"id": "h3Ohd508knYORidVTIcLH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -376.18215767308874,
			"y": -469.9405315726831,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 210.2905233630223,
			"height": 90.31147319314641,
			"seed": 42408395,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "30bpV1j8"
				},
				{
					"id": "7RIwvgRP8QZ2FM5HOnaRv",
					"type": "arrow"
				}
			],
			"updated": 1727006084444,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 444,
			"versionNonce": 578596069,
			"isDeleted": false,
			"id": "30bpV1j8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -350.61682152868696,
			"y": -437.2847949761099,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 159.15985107421875,
			"height": 25,
			"seed": 1438099563,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084445,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Model::transform",
			"rawText": "Model::transform",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "h3Ohd508knYORidVTIcLH",
			"originalText": "Model::transform",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 628,
			"versionNonce": 2136016837,
			"isDeleted": false,
			"id": "Do94x1moIkznu_jRQxol0",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -268.9041832466783,
			"y": -217.6335418634692,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 2.6909747972311493,
			"height": 114.29334889187339,
			"seed": 488465163,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "cXheyDWH"
				}
			],
			"updated": 1727006144441,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "-8qdJNXydQeIpWgJ6cj1G",
				"gap": 7.08109803817365,
				"focus": -0.014278973624994227
			},
			"endBinding": {
				"elementId": "RfC5eXQI6G8AOcX6lLcXI",
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
					2.6909747972311493,
					114.29334889187339
				]
			]
		},
		{
			"type": "text",
			"version": 48,
			"versionNonce": 1115670597,
			"isDeleted": false,
			"id": "cXheyDWH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -328.51864152677365,
			"y": -172.98686741753272,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 121.91989135742188,
			"height": 25,
			"seed": 1899153835,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084445,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Source Path",
			"rawText": "Source Path",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "Do94x1moIkznu_jRQxol0",
			"originalText": "Source Path",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 632,
			"versionNonce": 1207476709,
			"isDeleted": false,
			"id": "j0Y-vX54gnnJeI0aIkLaO",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -377.1770418121259,
			"y": -333.70274817502514,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 210.2905233630223,
			"height": 90.31147319314641,
			"seed": 1998740555,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "7RIwvgRP8QZ2FM5HOnaRv",
					"type": "arrow"
				},
				{
					"type": "text",
					"id": "E94ACQrt"
				}
			],
			"updated": 1727006084444,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 66,
			"versionNonce": 1581807525,
			"isDeleted": false,
			"id": "E94ACQrt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -326.1817282507319,
			"y": -301.04701157845193,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 108.29989624023438,
			"height": 25,
			"seed": 1254529771,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084446,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "TextWriter",
			"rawText": "TextWriter",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "j0Y-vX54gnnJeI0aIkLaO",
			"originalText": "TextWriter",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 410,
			"versionNonce": 2189157,
			"isDeleted": false,
			"id": "7RIwvgRP8QZ2FM5HOnaRv",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -273.2386053059813,
			"y": -376.7315383130549,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.183029599680708,
			"height": 41.95271266376767,
			"seed": 682231179,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144442,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "h3Ohd508knYORidVTIcLH",
				"gap": 2.897520066481775,
				"focus": 0.03342243207709898
			},
			"endBinding": {
				"elementId": "j0Y-vX54gnnJeI0aIkLaO",
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
					1.183029599680708,
					41.95271266376767
				]
			]
		},
		{
			"type": "arrow",
			"version": 364,
			"versionNonce": 1430258149,
			"isDeleted": false,
			"id": "7vp8C1qyiKu3LUzC1XIWu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -136.30375195234726,
			"y": 231.5765405850318,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 186.88453254508707,
			"height": 1.5058122607466657,
			"seed": 1476860971,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "5gzNiyV8"
				}
			],
			"updated": 1727006144441,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "6jV65uDaRXHqh7u4F9LF3",
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
					186.88453254508707,
					-1.5058122607466657
				]
			]
		},
		{
			"type": "text",
			"version": 24,
			"versionNonce": 268712709,
			"isDeleted": false,
			"id": "5gzNiyV8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -62.54147835558496,
			"y": 218.32363445465836,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 39.3599853515625,
			"height": 25,
			"seed": 1422417611,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006084446,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "JAR",
			"rawText": "JAR",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "7vp8C1qyiKu3LUzC1XIWu",
			"originalText": "JAR",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 702,
			"versionNonce": 1425916197,
			"isDeleted": false,
			"id": "Lcd8Sjm8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 22.685934815596283,
			"y": -1126.039792672996,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 810.139404296875,
			"height": 100,
			"seed": 1338131499,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006152561,
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
			"version": 528,
			"versionNonce": 1683248261,
			"isDeleted": false,
			"id": "bCKopXby",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 16.577571892529704,
			"y": -940.9391819742814,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 661.3394775390625,
			"height": 50,
			"seed": 2061403851,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006152561,
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
			"version": 686,
			"versionNonce": 1977741669,
			"isDeleted": false,
			"id": "lyjikFFH3YmCDuWMBhA49",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 383.09575454472724,
			"y": -339.69587384679994,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1538.878451759293,
			"height": 854.9576026945601,
			"seed": 147570533,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 376,
			"versionNonce": 1908964549,
			"isDeleted": false,
			"id": "eGA4HeB4",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 456.68321597623253,
			"y": -298.1285800259318,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 209.53982543945312,
			"height": 25,
			"seed": 1648921285,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
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
			"version": 827,
			"versionNonce": 2072419365,
			"isDeleted": false,
			"id": "6HcuIbislz5-do5d4zhUk",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 822.2860446700906,
			"y": -113.7153894664059,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 204.01398002812687,
			"height": 112.58660299607754,
			"seed": 1020586533,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "XnAlE70p"
				},
				{
					"id": "SUHtFZGV1Pk-XDNPnxSPk",
					"type": "arrow"
				},
				{
					"id": "9ZffXeguLYEYrpldZ_MY6",
					"type": "arrow"
				},
				{
					"id": "jbwKcpvZdpcYEolHxewnG",
					"type": "arrow"
				},
				{
					"id": "epdxVS6YEb7rhkOyyjvH2",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 750,
			"versionNonce": 453627781,
			"isDeleted": false,
			"id": "XnAlE70p",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 875.6330767984118,
			"y": -69.92208796836712,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 97.31991577148438,
			"height": 25,
			"seed": 2036227461,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "DiSLClass",
			"rawText": "DiSLClass",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "6HcuIbislz5-do5d4zhUk",
			"originalText": "DiSLClass",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 548,
			"versionNonce": 1585347685,
			"isDeleted": false,
			"id": "m9s8_fouISSK4h7_tDbNL",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 447.4625164831814,
			"y": -28.15565139828209,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 181.6207563719618,
			"height": 104.95952121530445,
			"seed": 2132960485,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "qLoTDm4L"
				},
				{
					"id": "9ZffXeguLYEYrpldZ_MY6",
					"type": "arrow"
				},
				{
					"id": "08OoLG1oJRhWPFsaU0T6h",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 483,
			"versionNonce": 1285269445,
			"isDeleted": false,
			"id": "qLoTDm4L",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 472.51300697384977,
			"y": -13.175890790629865,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 131.519775390625,
			"height": 75,
			"seed": 1907261509,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Package\n----------------\n- name",
			"rawText": "Package\n----------------\n- name",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "m9s8_fouISSK4h7_tDbNL",
			"originalText": "Package\n----------------\n- name",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 845,
			"versionNonce": 1259250149,
			"isDeleted": false,
			"id": "a02uwIwbCZurI0qlo9mNN",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 448.6917623916115,
			"y": -190.62456424907737,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 186.13844283808703,
			"height": 103.80918438365197,
			"seed": 652226469,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "qyRNEAm9"
				},
				{
					"id": "jbwKcpvZdpcYEolHxewnG",
					"type": "arrow"
				},
				{
					"id": "08OoLG1oJRhWPFsaU0T6h",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 817,
			"versionNonce": 1326128453,
			"isDeleted": false,
			"id": "qyRNEAm9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 509.561017379991,
			"y": -151.21997205725137,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 64.39993286132812,
			"height": 25,
			"seed": 1558508293,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Import",
			"rawText": "Import",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "a02uwIwbCZurI0qlo9mNN",
			"originalText": "Import",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 593,
			"versionNonce": 2004766725,
			"isDeleted": false,
			"id": "7s2UzpuKkJjRCmtzc_JEB",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1197.009480331063,
			"y": -109.72882200051458,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 261.76786071544575,
			"height": 103.167784453276,
			"seed": 809126501,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "7h5KDroU"
				},
				{
					"id": "JVhLoN_7sD657Oww_s9xV",
					"type": "arrow"
				},
				{
					"id": "xszlSyl3BNxF8heMRkZiY",
					"type": "arrow"
				},
				{
					"id": "g85mE5XTWKEVVs3Xli6-X",
					"type": "arrow"
				},
				{
					"id": "JVAQ4oP4dCR2MsvITXLbn",
					"type": "arrow"
				},
				{
					"id": "SUHtFZGV1Pk-XDNPnxSPk",
					"type": "arrow"
				},
				{
					"id": "pJscZpHcVzQj6rs53irSv",
					"type": "arrow"
				},
				{
					"id": "epdxVS6YEb7rhkOyyjvH2",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 570,
			"versionNonce": 1235224421,
			"isDeleted": false,
			"id": "7h5KDroU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1209.9235162796062,
			"y": -95.64492977387658,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 235.93978881835938,
			"height": 75,
			"seed": 1370338757,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Instrumentation Method\n----------------\n- name",
			"rawText": "Instrumentation Method\n----------------\n- name",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "7s2UzpuKkJjRCmtzc_JEB",
			"originalText": "Instrumentation Method\n----------------\n- name",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 724,
			"versionNonce": 798141669,
			"isDeleted": false,
			"id": "WeuloebsWA1RvatcKC1Yd",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 803.9440749549867,
			"y": 120.27875925348621,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 211.73866614613144,
			"height": 114.37136801973202,
			"seed": 2086450469,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "oBdnkh2N"
				},
				{
					"id": "JVAQ4oP4dCR2MsvITXLbn",
					"type": "arrow"
				},
				{
					"id": "g85mE5XTWKEVVs3Xli6-X",
					"type": "arrow"
				},
				{
					"id": "xszlSyl3BNxF8heMRkZiY",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 707,
			"versionNonce": 1020643397,
			"isDeleted": false,
			"id": "oBdnkh2N",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 829.1934815754157,
			"y": 139.96444326335222,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 161.23985290527344,
			"height": 75,
			"seed": 733268101,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Annotation\n----------------\n- activationTime",
			"rawText": "Annotation\n----------------\n- activationTime",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "WeuloebsWA1RvatcKC1Yd",
			"originalText": "Annotation\n----------------\n- activationTime",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 879,
			"versionNonce": 1257307589,
			"isDeleted": false,
			"id": "oIkeAwSbdlHdJxpUVjHTw",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 634.5945537602302,
			"y": 355.03632037751163,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 220.1535543631271,
			"height": 112.28681824601063,
			"seed": 292777957,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "bHAl32JU"
				},
				{
					"id": "g85mE5XTWKEVVs3Xli6-X",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 848,
			"versionNonce": 2113338661,
			"isDeleted": false,
			"id": "bHAl32JU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 677.021390451071,
			"y": 373.67972950051694,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 135.2998809814453,
			"height": 75,
			"seed": 1269181253,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Marker\n----------------\n- markerClass",
			"rawText": "Marker\n----------------\n- markerClass",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "oIkeAwSbdlHdJxpUVjHTw",
			"originalText": "Marker\n----------------\n- markerClass",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 850,
			"versionNonce": 387692517,
			"isDeleted": false,
			"id": "3LHDcG7Fa1merGRZvHcf2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 917.4204585290927,
			"y": 360.0816330515984,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 1521121957,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "K8J6EW1f"
				},
				{
					"id": "xszlSyl3BNxF8heMRkZiY",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 835,
			"versionNonce": 84243269,
			"isDeleted": false,
			"id": "K8J6EW1f",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 925.7832065973457,
			"y": 376.2814478746393,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 177.33981323242188,
			"height": 75,
			"seed": 1462623749,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Scope\n----------------\n- methodIdentifier",
			"rawText": "Scope\n----------------\n- methodIdentifier",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "3LHDcG7Fa1merGRZvHcf2",
			"originalText": "Scope\n----------------\n- methodIdentifier",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 849,
			"versionNonce": 660284933,
			"isDeleted": false,
			"id": "ToFOEtK_uZpSKKwk5kAwP",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1450.39945010474,
			"y": 134.60039798056027,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 193.7376376653664,
			"height": 104.25724900384098,
			"seed": 1747834213,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "MBdKRmYS"
				},
				{
					"id": "JVhLoN_7sD657Oww_s9xV",
					"type": "arrow"
				},
				{
					"id": "AEqqBzMbPCFNJY8UQYDbh",
					"type": "arrow"
				},
				{
					"id": "Mqcaq-UoDtQGl6SySfJSz",
					"type": "arrow"
				},
				{
					"id": "Q2HYA7RgIsTOBKUH7ZUc1",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 769,
			"versionNonce": 1399987557,
			"isDeleted": false,
			"id": "MBdKRmYS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1476.018337601974,
			"y": 149.22902248248076,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 142.49986267089844,
			"height": 75,
			"seed": 2029510853,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Exportable\n----------------\n- varIdentifier",
			"rawText": "Exportable\n----------------\n- varIdentifier",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "ToFOEtK_uZpSKKwk5kAwP",
			"originalText": "Exportable\n----------------\n- varIdentifier",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1293,
			"versionNonce": 1836669957,
			"isDeleted": false,
			"id": "JVhLoN_7sD657Oww_s9xV",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1423.8676011970251,
			"y": -1.7714532845413942,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 82.20549912845286,
			"height": 120.56908085821671,
			"seed": 791248933,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "k2N3E6NL"
				}
			],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "7s2UzpuKkJjRCmtzc_JEB",
				"gap": 4.78958426269719,
				"focus": -0.3465010792951129
			},
			"endBinding": {
				"elementId": "ToFOEtK_uZpSKKwk5kAwP",
				"gap": 15.802770406884974,
				"focus": 0.0386773803304945
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
					82.20549912845286,
					120.56908085821671
				]
			]
		},
		{
			"type": "text",
			"version": 39,
			"versionNonce": 367719819,
			"isDeleted": false,
			"id": "k2N3E6NL",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1299.7068822920642,
			"y": -26.83634199179005,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 73.87991333007812,
			"height": 25,
			"seed": 548418437,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "exports",
			"rawText": "exports",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "JVhLoN_7sD657Oww_s9xV",
			"originalText": "exports",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 2266,
			"versionNonce": 1648965,
			"isDeleted": false,
			"id": "xszlSyl3BNxF8heMRkZiY",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 949.9502261030743,
			"y": 241.39010890572854,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 74.14957644379456,
			"height": 110.48128218702618,
			"seed": 1237940965,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "InvDj0Jn"
				}
			],
			"updated": 1727006144443,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "WeuloebsWA1RvatcKC1Yd",
				"gap": 6.7399816325103075,
				"focus": 0.019181547721148783
			},
			"endBinding": {
				"elementId": "3LHDcG7Fa1merGRZvHcf2",
				"gap": 8.210241958843653,
				"focus": 0.3847327256333408
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
					74.14957644379456,
					110.48128218702618
				]
			]
		},
		{
			"type": "text",
			"version": 44,
			"versionNonce": 1336544299,
			"isDeleted": false,
			"id": "InvDj0Jn",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 791.0015666077379,
			"y": 211.28132086288383,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 135.39987182617188,
			"height": 25,
			"seed": 1124259397,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "targetMethod",
			"rawText": "targetMethod",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "xszlSyl3BNxF8heMRkZiY",
			"originalText": "targetMethod",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 2429,
			"versionNonce": 976176773,
			"isDeleted": false,
			"id": "g85mE5XTWKEVVs3Xli6-X",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 846.61874050405,
			"y": 247.4904158062218,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 71.16282996021891,
			"height": 87.45587283166901,
			"seed": 926533029,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "kZV7HJ63"
				}
			],
			"updated": 1727006144443,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "WeuloebsWA1RvatcKC1Yd",
				"gap": 12.840288533003559,
				"focus": 0.04077750037535953
			},
			"endBinding": {
				"elementId": "oIkeAwSbdlHdJxpUVjHTw",
				"gap": 20.090031739620827,
				"focus": -0.2006062324138537
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
					87.45587283166901
				]
			]
		},
		{
			"type": "text",
			"version": 38,
			"versionNonce": 982339275,
			"isDeleted": false,
			"id": "kZV7HJ63",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 650.7738417959645,
			"y": 205.86892308569918,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 63.87994384765625,
			"height": 25,
			"seed": 429639941,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "marker",
			"rawText": "marker",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "g85mE5XTWKEVVs3Xli6-X",
			"originalText": "marker",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1681,
			"versionNonce": 629112389,
			"isDeleted": false,
			"id": "JVAQ4oP4dCR2MsvITXLbn",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1206.389047577657,
			"y": 3.2063679142445523,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 225.69375270594094,
			"height": 105.31606169303016,
			"seed": 463888485,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "DimuyQsQ"
				}
			],
			"updated": 1727006144443,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "7s2UzpuKkJjRCmtzc_JEB",
				"gap": 9.767405461483136,
				"focus": -0.0413053358141833
			},
			"endBinding": {
				"elementId": "WeuloebsWA1RvatcKC1Yd",
				"gap": 11.756329646211498,
				"focus": -0.3364946676192887
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
					-225.69375270594094,
					105.31606169303016
				]
			]
		},
		{
			"type": "text",
			"version": 72,
			"versionNonce": 2033139051,
			"isDeleted": false,
			"id": "DimuyQsQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 913.2986993985669,
			"y": -29.48503037559749,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 103.83992004394531,
			"height": 25,
			"seed": 1663503301,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "annotation",
			"rawText": "annotation",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "JVAQ4oP4dCR2MsvITXLbn",
			"originalText": "annotation",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 467,
			"versionNonce": 1222267845,
			"isDeleted": false,
			"id": "lfIjSvUb",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1524.9090506877756,
			"y": 109.75507805629354,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 10.319992065429688,
			"height": 25,
			"seed": 3885861,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "JVhLoN_7sD657Oww_s9xV",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
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
			"version": 1006,
			"versionNonce": 1222677029,
			"isDeleted": false,
			"id": "SUHtFZGV1Pk-XDNPnxSPk",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1034.2737095272507,
			"y": -55.41036256954865,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 142.56462505851437,
			"height": 1.509879026758739,
			"seed": 592486021,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "b9rOm2t0"
				}
			],
			"updated": 1727006144442,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "6HcuIbislz5-do5d4zhUk",
				"gap": 7.973684829033118,
				"focus": 0.01476179189993838
			},
			"endBinding": {
				"elementId": "I2NAW9hE",
				"gap": 6.2941410199161965,
				"focus": -1.5025936831977968
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
					142.56462505851437,
					1.509879026758739
				]
			]
		},
		{
			"type": "text",
			"version": 37,
			"versionNonce": 1994020875,
			"isDeleted": false,
			"id": "b9rOm2t0",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 957.3958411737949,
			"y": -139.99899220255452,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 40.77995300292969,
			"height": 25,
			"seed": 321160677,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "logic",
			"rawText": "logic",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "SUHtFZGV1Pk-XDNPnxSPk",
			"originalText": "logic",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 319,
			"versionNonce": 449963653,
			"isDeleted": false,
			"id": "I2NAW9hE",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1179.4317159971333,
			"y": -85.18290458276238,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 10.319992065429688,
			"height": 25,
			"seed": 1657045317,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "SUHtFZGV1Pk-XDNPnxSPk",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
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
			"version": 1148,
			"versionNonce": 921066949,
			"isDeleted": false,
			"id": "9ZffXeguLYEYrpldZ_MY6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 816.2708494687042,
			"y": -50.43709669084152,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 176.74579812266506,
			"height": 84.38112451337525,
			"seed": 1737363621,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "mzgZrip5"
				}
			],
			"updated": 1727006144442,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "6HcuIbislz5-do5d4zhUk",
				"gap": 6.015195201386518,
				"focus": 0.4246611138225677
			},
			"endBinding": {
				"elementId": "m9s8_fouISSK4h7_tDbNL",
				"gap": 10.441778490896013,
				"focus": 0.6047877194062723
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
					-176.74579812266506,
					84.38112451337525
				]
			]
		},
		{
			"type": "text",
			"version": 36,
			"versionNonce": 1367940779,
			"isDeleted": false,
			"id": "mzgZrip5",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 560.9344697311537,
			"y": -93.59596357051146,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 77.27993774414062,
			"height": 25,
			"seed": 1865331717,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "package",
			"rawText": "package",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "9ZffXeguLYEYrpldZ_MY6",
			"originalText": "package",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1033,
			"versionNonce": 467130277,
			"isDeleted": false,
			"id": "jbwKcpvZdpcYEolHxewnG",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 803.282456031214,
			"y": -59.990166939584654,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 155.9652822165599,
			"height": 73.78898348147008,
			"seed": 899935077,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [
				{
					"type": "text",
					"id": "xUlhX6dD"
				}
			],
			"updated": 1727006144442,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "6HcuIbislz5-do5d4zhUk",
				"gap": 19.00358863887675,
				"focus": -0.5230161783082306
			},
			"endBinding": {
				"elementId": "g7hfsbPh",
				"gap": 4.1189000814752035,
				"focus": 1.2012376330247054
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
					-155.9652822165599,
					-73.78898348147008
				]
			]
		},
		{
			"type": "text",
			"version": 35,
			"versionNonce": 65409355,
			"isDeleted": false,
			"id": "xUlhX6dD",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 562.6063461485712,
			"y": -182.23408781667717,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 68.73991394042969,
			"height": 25,
			"seed": 456865477,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006124814,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "imports",
			"rawText": "imports",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "jbwKcpvZdpcYEolHxewnG",
			"originalText": "imports",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1497,
			"versionNonce": 989791141,
			"isDeleted": false,
			"id": "epdxVS6YEb7rhkOyyjvH2",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1226.4870623597967,
			"y": -485.0552859863429,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 291.40724794212156,
			"height": 349.6144575286094,
			"seed": 445781541,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "UQ20XurYWnj-antpsMdNo",
				"gap": 8.613498447226902,
				"focus": -0.030487837793552082
			},
			"endBinding": {
				"elementId": "6HcuIbislz5-do5d4zhUk",
				"gap": 21.725438991327565,
				"focus": -0.11694128780078238
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
					-247.46813823528817,
					211.43535719628437
				],
				[
					-291.40724794212156,
					349.6144575286094
				]
			]
		},
		{
			"type": "rectangle",
			"version": 743,
			"versionNonce": 1800942437,
			"isDeleted": false,
			"id": "UQ20XurYWnj-antpsMdNo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1235.1005608070236,
			"y": -683.5642713632449,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 191.4683882881172,
			"height": 207.39918854404885,
			"seed": 2123762053,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "epdxVS6YEb7rhkOyyjvH2",
					"type": "arrow"
				},
				{
					"type": "text",
					"id": "PGMcL9QY"
				},
				{
					"id": "pJscZpHcVzQj6rs53irSv",
					"type": "arrow"
				},
				{
					"id": "80rTLsm27RFxqysfsFUm7",
					"type": "arrow"
				},
				{
					"id": "0pyrPB8bsYBGyuhqSxhli",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 681,
			"versionNonce": 589725381,
			"isDeleted": false,
			"id": "PGMcL9QY",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1265.0748672557697,
			"y": -617.3646770912204,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 131.519775390625,
			"height": 75,
			"seed": 1485082853,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Metaclass\n----------------\n+ visit()",
			"rawText": "Metaclass\n----------------\n+ visit()",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "UQ20XurYWnj-antpsMdNo",
			"originalText": "Metaclass\n----------------\n+ visit()",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 1317,
			"versionNonce": 28581477,
			"isDeleted": false,
			"id": "pJscZpHcVzQj6rs53irSv",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1318.5046784539293,
			"y": -119.46806413243144,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 1.9788401715793498,
			"height": 352.05392556454217,
			"seed": 904604741,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "7s2UzpuKkJjRCmtzc_JEB",
				"gap": 9.739242131916853,
				"focus": -0.06894699452887318
			},
			"endBinding": {
				"elementId": "UQ20XurYWnj-antpsMdNo",
				"gap": 4.643093122222382,
				"focus": 0.15488317369333976
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
					-1.9788401715793498,
					-352.05392556454217
				]
			]
		},
		{
			"type": "arrow",
			"version": 1155,
			"versionNonce": 2093812165,
			"isDeleted": false,
			"id": "80rTLsm27RFxqysfsFUm7",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1397.378966885687,
			"y": -471.3631713479988,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 165.235967146863,
			"height": 513.6929507476602,
			"seed": 380066725,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "UQ20XurYWnj-antpsMdNo",
				"gap": 4.801911471197201,
				"focus": 0.05519350287985601
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
					236.4340948327963
				],
				[
					93.71250338786194,
					513.6929507476602
				]
			]
		},
		{
			"type": "arrow",
			"version": 926,
			"versionNonce": 812747045,
			"isDeleted": false,
			"id": "0pyrPB8bsYBGyuhqSxhli",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1225.708013197655,
			"y": -565.5754195493869,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 473.82785382035127,
			"height": 429.25658165688924,
			"seed": 585539333,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "UQ20XurYWnj-antpsMdNo",
				"gap": 9.392547609368648,
				"focus": 0.37925636190420947
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
					-384.8824855205544,
					299.86839045762986
				],
				[
					-473.82785382035127,
					429.25658165688924
				]
			]
		},
		{
			"type": "arrow",
			"version": 1103,
			"versionNonce": 457888581,
			"isDeleted": false,
			"id": "08OoLG1oJRhWPFsaU0T6h",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 548.4895226538929,
			"y": -81.12615501302494,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.5199019032501155,
			"height": 51.85467123969274,
			"seed": 556168805,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144442,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "a02uwIwbCZurI0qlo9mNN",
				"gap": 5.689224852400443,
				"focus": -0.08897988699866209
			},
			"endBinding": {
				"elementId": "m9s8_fouISSK4h7_tDbNL",
				"gap": 1.1158323750501182,
				"focus": 0.0771619300983562
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
					-1.5199019032501155,
					51.85467123969274
				]
			]
		},
		{
			"type": "rectangle",
			"version": 932,
			"versionNonce": 128461093,
			"isDeleted": false,
			"id": "SQ9rRJ55G5pU70CkAZoYF",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 502.92126497687605,
			"y": -683.6572329215776,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 257.8881015044217,
			"height": 212.03862339834996,
			"seed": 741630405,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "H7fy2xLa"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 848,
			"versionNonce": 628210821,
			"isDeleted": false,
			"id": "H7fy2xLa",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 566.1054280337744,
			"y": -627.6379212224026,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 131.519775390625,
			"height": 100,
			"seed": 690872613,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Model\n----------------\n- dislClass\n+ visit()",
			"rawText": "Model\n----------------\n- dislClass\n+ visit()",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "SQ9rRJ55G5pU70CkAZoYF",
			"originalText": "Model\n----------------\n- dislClass\n+ visit()",
			"lineHeight": 1.25
		},
		{
			"type": "text",
			"version": 180,
			"versionNonce": 795993061,
			"isDeleted": false,
			"id": "g7hfsbPh",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 644.9664440666829,
			"y": -162.89805050252994,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 10.319992065429688,
			"height": 25,
			"seed": 2105570437,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [
				{
					"id": "jbwKcpvZdpcYEolHxewnG",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
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
			"version": 786,
			"versionNonce": 983297701,
			"isDeleted": false,
			"id": "TebJkwXrCtS8RmzJhZoYS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1246.2237359581513,
			"y": 365.03174856374994,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 82418661,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "RAUFJIgC"
				},
				{
					"id": "AEqqBzMbPCFNJY8UQYDbh",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 792,
			"versionNonce": 550556165,
			"isDeleted": false,
			"id": "RAUFJIgC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1299.5864306206427,
			"y": 406.23156338679087,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 87.33992004394531,
			"height": 25,
			"seed": 1198970693,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Argument",
			"rawText": "Argument",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "TebJkwXrCtS8RmzJhZoYS",
			"originalText": "Argument",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 780,
			"versionNonce": 1164825797,
			"isDeleted": false,
			"id": "0BSm204t-tbDvfjhvxoPu",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1462.3761912615892,
			"y": 364.5687724617958,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 1871029925,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "YXjV3ieV"
				},
				{
					"id": "Mqcaq-UoDtQGl6SySfJSz",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 781,
			"versionNonce": 1623258149,
			"isDeleted": false,
			"id": "YXjV3ieV",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1537.6888676135336,
			"y": 405.7685872848367,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 43.43995666503906,
			"height": 25,
			"seed": 2143521285,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Field",
			"rawText": "Field",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "0BSm204t-tbDvfjhvxoPu",
			"originalText": "Field",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 762,
			"versionNonce": 1796468453,
			"isDeleted": false,
			"id": "C72P0yBkPclYDtBczAhrt",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1675.4546679191508,
			"y": 364.0256047712494,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 194.065309368928,
			"height": 107.39962964608185,
			"seed": 1227751781,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "WqyLX9QA"
				},
				{
					"id": "Q2HYA7RgIsTOBKUH7ZUc1",
					"type": "arrow"
				}
			],
			"updated": 1727006127062,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 760,
			"versionNonce": 987679301,
			"isDeleted": false,
			"id": "WqyLX9QA",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1734.307352816017,
			"y": 405.2254195942903,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 76.35993957519531,
			"height": 25,
			"seed": 554863813,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006127062,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Variable",
			"rawText": "Variable",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "C72P0yBkPclYDtBczAhrt",
			"originalText": "Variable",
			"lineHeight": 1.25
		},
		{
			"type": "arrow",
			"version": 754,
			"versionNonce": 2103657445,
			"isDeleted": false,
			"id": "AEqqBzMbPCFNJY8UQYDbh",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1412.9229285588158,
			"y": 350.8909870096088,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 87.56668803668731,
			"height": 104.64592631896318,
			"seed": 1460579365,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "TebJkwXrCtS8RmzJhZoYS",
				"gap": 14.140761554141136,
				"focus": 0.09085283043696135
			},
			"endBinding": {
				"elementId": "ToFOEtK_uZpSKKwk5kAwP",
				"gap": 7.387413706244388,
				"focus": -0.0215232145043588
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
					87.56668803668731,
					-104.64592631896318
				]
			]
		},
		{
			"type": "arrow",
			"version": 617,
			"versionNonce": 62213797,
			"isDeleted": false,
			"id": "Mqcaq-UoDtQGl6SySfJSz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1561.8638572917057,
			"y": 347.360425114063,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 1.5486130435872383,
			"height": 100.98894951169956,
			"seed": 1796202373,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "0BSm204t-tbDvfjhvxoPu",
				"gap": 17.208347347732797,
				"focus": 0.03619958966411002
			},
			"endBinding": {
				"elementId": "ToFOEtK_uZpSKKwk5kAwP",
				"gap": 7.51382861796219,
				"focus": -0.12422048588843457
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
					-1.5486130435872383,
					-100.98894951169956
				]
			]
		},
		{
			"type": "arrow",
			"version": 595,
			"versionNonce": 60008805,
			"isDeleted": false,
			"id": "Q2HYA7RgIsTOBKUH7ZUc1",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1741.739282873931,
			"y": 350.46638206788543,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 133.75444136263445,
			"height": 106.58835919364134,
			"seed": 55813861,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006144444,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "C72P0yBkPclYDtBczAhrt",
				"gap": 13.559222703363957,
				"focus": 0.326320464640924
			},
			"endBinding": {
				"elementId": "ToFOEtK_uZpSKKwk5kAwP",
				"gap": 5.020375889842853,
				"focus": 0.06776997729987175
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
					-133.75444136263445,
					-106.58835919364134
				]
			]
		},
		{
			"type": "rectangle",
			"version": 686,
			"versionNonce": 1232971339,
			"isDeleted": false,
			"id": "e0GXbPrMogUTWvOKCc926",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -327.33224851654677,
			"y": -1873.4492475332722,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 702.4069312848604,
			"height": 571.6994988740456,
			"seed": 2052283979,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "LincC3KJ0spbw9egVEPBm",
					"type": "arrow"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 595,
			"versionNonce": 1614466955,
			"isDeleted": false,
			"id": "JjpNZSnW",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -300.04763790382503,
			"y": -1837.4901518279717,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 185.75985717773438,
			"height": 25,
			"seed": 75980011,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
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
			"version": 670,
			"versionNonce": 921609771,
			"isDeleted": false,
			"id": "N1TyDhs9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": -287.41256000425756,
			"y": -1729.4264566265406,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 503.47967529296875,
			"height": 350,
			"seed": 822249355,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
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
			"type": "rectangle",
			"version": 652,
			"versionNonce": 1110354123,
			"isDeleted": false,
			"id": "iyy-urdvRaoRrHOKu5S7a",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 818.6700234274954,
			"y": -1852.7579136643658,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 501.2913950025726,
			"height": 77.82345407099206,
			"seed": 100621867,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 518,
			"versionNonce": 2006275947,
			"isDeleted": false,
			"id": "pzQFrZ9l",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 831.7479449515463,
			"y": -1827.305528497754,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 77.27993774414062,
			"height": 25,
			"seed": 1583898827,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
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
			"version": 491,
			"versionNonce": 896348683,
			"isDeleted": false,
			"id": "zFauEsksu-tF0tYjYx0lg",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 929.7113068114099,
			"y": -1842.5486385661288,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 229.61961549807074,
			"height": 58.204287830755675,
			"seed": 1406668651,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "unPkX3IS"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 477,
			"versionNonce": 749779115,
			"isDeleted": false,
			"id": "unPkX3IS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 961.9311563695273,
			"y": -1825.946494650751,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 165.17991638183594,
			"height": 25,
			"seed": 1754851851,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "PACKAGE_NAME",
			"rawText": "PACKAGE_NAME",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "zFauEsksu-tF0tYjYx0lg",
			"originalText": "PACKAGE_NAME",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 639,
			"versionNonce": 185232203,
			"isDeleted": false,
			"id": "CMkDnDW52ufSR-CStghbS",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 816.2490320171751,
			"y": -1760.6780177224123,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 508.25288728432315,
			"height": 99.10990684852459,
			"seed": 1906667691,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "6sa9bsE8"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 562,
			"versionNonce": 1691666923,
			"isDeleted": false,
			"id": "6sa9bsE8",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1032.7455165528913,
			"y": -1723.62306429815,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 75.25991821289062,
			"height": 25,
			"seed": 1084760907,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "Imports",
			"rawText": "Imports",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "CMkDnDW52ufSR-CStghbS",
			"originalText": "Imports",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 784,
			"versionNonce": 1595561099,
			"isDeleted": false,
			"id": "W9IQbuOlEjsSNvefu_Mjo",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 817.1763174064113,
			"y": -1644.0775899114042,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 513.2090678129974,
			"height": 433.2669240181397,
			"seed": 234586603,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"id": "LincC3KJ0spbw9egVEPBm",
					"type": "arrow"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 492,
			"versionNonce": 1487990219,
			"isDeleted": false,
			"id": "FEnNVw67",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 839.7052412473536,
			"y": -1619.4839715092075,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 111.41989135742188,
			"height": 25,
			"seed": 581569675,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
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
			"version": 544,
			"versionNonce": 1871117419,
			"isDeleted": false,
			"id": "l45kBXgpnjjCkoKnOBARU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 968.3558112101068,
			"y": -1627.587440871299,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 199.33895123578236,
			"height": 40.412284919062586,
			"seed": 1114522411,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "CyxhAhUr"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 479,
			"versionNonce": 1776432907,
			"isDeleted": false,
			"id": "CyxhAhUr",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1001.215327416377,
			"y": -1619.8812984117676,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 133.6199188232422,
			"height": 25,
			"seed": 1437771211,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "CLASS_NAME",
			"rawText": "CLASS_NAME",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "l45kBXgpnjjCkoKnOBARU",
			"originalText": "CLASS_NAME",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1087,
			"versionNonce": 1983716779,
			"isDeleted": false,
			"id": "scQ9Dl37Zt7gidN6H6dCb",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 838.9630041944934,
			"y": -1577.8109438934503,
			"strokeColor": "#e03131",
			"backgroundColor": "transparent",
			"width": 473.53678404199536,
			"height": 342.75729856935334,
			"seed": 1402057835,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 475,
			"versionNonce": 264384587,
			"isDeleted": false,
			"id": "14T2GbeW",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 854.5874865583414,
			"y": -1497.7446468366707,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 179.579833984375,
			"height": 25,
			"seed": 1809182475,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
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
			"version": 532,
			"versionNonce": 1749806827,
			"isDeleted": false,
			"id": "BU8XLnNSENLCwVMZsVKhh",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1039.4004893322021,
			"y": -1502.7190989986302,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 242.01691868223952,
			"height": 35,
			"seed": 1895421355,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "ngq5TkdC"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 521,
			"versionNonce": 308599179,
			"isDeleted": false,
			"id": "ngq5TkdC",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1083.1290032997867,
			"y": -1497.7190989986302,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 154.5598907470703,
			"height": 25,
			"seed": 36169803,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "INST_METHOD",
			"rawText": "INST_METHOD",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "BU8XLnNSENLCwVMZsVKhh",
			"originalText": "INST_METHOD",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 714,
			"versionNonce": 1799843883,
			"isDeleted": false,
			"id": "JD2dRKtBGR2EzN6PfiPMJ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 851.4950126247809,
			"y": -1563.9930212213394,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 431.05066874503564,
			"height": 50.96872104972341,
			"seed": 1966778091,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "rectangle",
			"version": 584,
			"versionNonce": 1155412683,
			"isDeleted": false,
			"id": "U3wdWicIRdOPutFv7yOeX",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 863.2254011940308,
			"y": -1556.2093054417433,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 157.117774621462,
			"height": 37.3797179926305,
			"seed": 12358027,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "lUbAUeQj"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 515,
			"versionNonce": 1338220907,
			"isDeleted": false,
			"id": "lUbAUeQj",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 873.2943364173595,
			"y": -1550.019446445428,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 136.9799041748047,
			"height": 25,
			"seed": 635971627,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "ANNOTATION",
			"rawText": "ANNOTATION",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "U3wdWicIRdOPutFv7yOeX",
			"originalText": "ANNOTATION",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 638,
			"versionNonce": 1161968651,
			"isDeleted": false,
			"id": "y4OHhKjE1alMA3RxweEFH",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1028.848621109863,
			"y": -1555.7981936928209,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 126.11538084439871,
			"height": 35,
			"seed": 1520954059,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "Bb4Ac1DY"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 465,
			"versionNonce": 1364492971,
			"isDeleted": false,
			"id": "Bb4Ac1DY",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1051.2563252649725,
			"y": -1550.7981936928209,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 81.29997253417969,
			"height": 25,
			"seed": 1939233131,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "MARKER",
			"rawText": "MARKER",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "y4OHhKjE1alMA3RxweEFH",
			"originalText": "MARKER",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 530,
			"versionNonce": 1017443659,
			"isDeleted": false,
			"id": "CMu2i0Tlwh9X54_t3pkdz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1167.2242678887892,
			"y": -1554.8252292203715,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 101.6222564252721,
			"height": 35,
			"seed": 1308488715,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "qxrWXPlz"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 462,
			"versionNonce": 1452151787,
			"isDeleted": false,
			"id": "qxrWXPlz",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1184.9054217361909,
			"y": -1549.8252292203715,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 66.25994873046875,
			"height": 25,
			"seed": 970537643,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "SCOPE",
			"rawText": "SCOPE",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "CMu2i0Tlwh9X54_t3pkdz",
			"originalText": "SCOPE",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 642,
			"versionNonce": 521821835,
			"isDeleted": false,
			"id": "hpbC0U063xKho7pc0Xpg9",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 854.9296672476389,
			"y": -1459.335083197073,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 431.0049896618225,
			"height": 205.7746011664788,
			"seed": 601009483,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "arrow",
			"version": 497,
			"versionNonce": 1961199973,
			"isDeleted": false,
			"id": "LincC3KJ0spbw9egVEPBm",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "dashed",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 386.66721641906634,
			"y": -1569.5747678255066,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 403.1405744119743,
			"height": 5.53174828807073,
			"seed": 2135215083,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 2
			},
			"boundElements": [],
			"updated": 1727006146455,
			"link": null,
			"locked": false,
			"startBinding": {
				"elementId": "e0GXbPrMogUTWvOKCc926",
				"focus": 0.044884672745720326,
				"gap": 11.59253365075267
			},
			"endBinding": {
				"elementId": "W9IQbuOlEjsSNvefu_Mjo",
				"focus": 0.6027690507782744,
				"gap": 27.36852657537065
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
			"type": "text",
			"version": 137,
			"versionNonce": 1807120331,
			"isDeleted": false,
			"id": "wK0A1Qxj",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 880.0410362217549,
			"y": -1442.7520614228283,
			"strokeColor": "#2f9e44",
			"backgroundColor": "transparent",
			"width": 134.59991455078125,
			"height": 25,
			"seed": 1882855051,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
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
			"version": 1226,
			"versionNonce": 1565214315,
			"isDeleted": false,
			"id": "2DKZ3nngttwGKwKtsTC-u",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 861.650651350054,
			"y": -1407.800285289154,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 408.01575642275156,
			"height": 38.68629691316938,
			"seed": 896339243,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "qHGqlOKQ"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1318,
			"versionNonce": 1821196555,
			"isDeleted": false,
			"id": "qHGqlOKQ",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1002.3285582479532,
			"y": -1400.9571368325694,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 126.65994262695312,
			"height": 25,
			"seed": 1380462539,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "ARGUMENTS",
			"rawText": "ARGUMENTS",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "2DKZ3nngttwGKwKtsTC-u",
			"originalText": "ARGUMENTS",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1220,
			"versionNonce": 1123477419,
			"isDeleted": false,
			"id": "JpmlNMQQVsYwR3mBxhryU",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 862.9100859756786,
			"y": -1358.2693678406913,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 408.8748329138988,
			"height": 38.68629691316938,
			"seed": 2028338795,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "dScp8SY6"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1244,
			"versionNonce": 1991424587,
			"isDeleted": false,
			"id": "dScp8SY6",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1010.7475268466906,
			"y": -1351.4262193841066,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 113.199951171875,
			"height": 25,
			"seed": 873527563,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "VARIABLES",
			"rawText": "VARIABLES",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "JpmlNMQQVsYwR3mBxhryU",
			"originalText": "VARIABLES",
			"lineHeight": 1.25
		},
		{
			"type": "rectangle",
			"version": 1334,
			"versionNonce": 1956329707,
			"isDeleted": false,
			"id": "IYtGrJicZWdC3CgPo1T9j",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 865.7415439259198,
			"y": -1310.3097761123609,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 406.66796377612525,
			"height": 37.09592659842293,
			"seed": 1363386283,
			"groupIds": [],
			"frameId": null,
			"roundness": {
				"type": 3
			},
			"boundElements": [
				{
					"type": "text",
					"id": "KFg8fMyF"
				}
			],
			"updated": 1727006146447,
			"link": null,
			"locked": false
		},
		{
			"type": "text",
			"version": 1351,
			"versionNonce": 708502411,
			"isDeleted": false,
			"id": "KFg8fMyF",
			"fillStyle": "solid",
			"strokeWidth": 2,
			"strokeStyle": "solid",
			"roughness": 1,
			"opacity": 100,
			"angle": 0,
			"x": 1031.2755456504083,
			"y": -1304.2618128131494,
			"strokeColor": "#1e1e1e",
			"backgroundColor": "transparent",
			"width": 75.59996032714844,
			"height": 25,
			"seed": 178811467,
			"groupIds": [],
			"frameId": null,
			"roundness": null,
			"boundElements": [],
			"updated": 1727006146447,
			"link": null,
			"locked": false,
			"fontSize": 20,
			"fontFamily": 1,
			"text": "FIELDS",
			"rawText": "FIELDS",
			"textAlign": "center",
			"verticalAlign": "middle",
			"containerId": "IYtGrJicZWdC3CgPo1T9j",
			"originalText": "FIELDS",
			"lineHeight": 1.25
		}
	],
	"appState": {
		"theme": "light",
		"viewBackgroundColor": "#ffffff",
		"currentItemStrokeColor": "#1e1e1e",
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
		"scrollX": 464.6748269232229,
		"scrollY": 1645.9125593008814,
		"zoom": {
			"value": 0.8500287494808436
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