var stompClient = null;
var viewer = null;

Cesium.Ion.defaultAccessToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiI3NDFkNGZjYS04MDdmLTQzMDEtODkxMC1hNTY5NDBlZjYzYzMiLCJpZCI6MTMxNzY5LCJpYXQiOjE2ODA0Nzk1NTJ9.QxpQgtigAYgFubiDaZgFz9jP8vaCKC3xCVALYfGJzo4';

function connect() {
    viewer = new Cesium.Viewer('cesiumContainer', {
        terrainProvider: Cesium.createWorldTerrain()
    });

    const buildingTileset = viewer.scene.primitives.add(Cesium.createOsmBuildings());
    // Fly the camera to San Francisco at the given longitude, latitude, and height.
    viewer.camera.flyTo({
        destination: Cesium.Cartesian3.fromDegrees(-122.4175, 37.655, 400),
        orientation: {
            heading: Cesium.Math.toRadians(0.0),
            pitch: Cesium.Math.toRadians(-15.0),
        }
    });

    var socket = new SockJS('localhost:8080/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            changeCoords(JSON.parse(greeting.body).content)
        });
    });
}


function changeCoords(coords) {
    const values = coords.split(',')

    console.log("longitude: " + values[0])
    console.log("latitude: " + values[1])
    console.log("altitude: ", + values[2])

    viewer.camera.flyTo({
        destination: Cesium.Cartesian3.fromDegrees(values[0], values[1], values[2]),
        orientation: {
            heading: Cesium.Math.toRadians(0.0),
            pitch: Cesium.Math.toRadians(-15.0),
        }
    });
}




