load('api_config.js');
load('api_mqtt.js');
load('api_dht.js');
load('api_timer.js');

let dht = DHT.create(16, DHT.DHT22);
let sensorType = 'DHT22';
let deviceId = Cfg.get('device.id');
let placement = Cfg.get('placement');

Timer.set(10 * 1000, Timer.REPEAT, function() {
  let sensorValue = {
    type: sensorType,
    deviceId: deviceId,
    placement: placement,
    temperature: dht.getTemp(),
    humidity: dht.getHumidity()
  };
  let message = JSON.stringify(sensorValue);
  let ok = MQTT.pub('piot/dht22/measurement', message, 1);
  print('mqtt measurement sent?', ok, message);
}, null);