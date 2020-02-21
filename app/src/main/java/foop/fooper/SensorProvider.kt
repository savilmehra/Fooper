package foop.fooper

import android.content.Context
import android.content.Intent
import android.view.View

import android.util.DisplayMetrics

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.*


public class SensorProvider : MainCardProvider(), SensorEventListener {
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    private var xValue: TextView? = null
    private var yValue: TextView? = null
    private var zValue: TextView? = null
    private var title: TextView? = null

    private var tvType: TextView? = null
    private var tvMfr: TextView? = null
    private var tvCons: TextView? = null
    private var tvReso: TextView? = null
    private var tvMinDelay: TextView? = null
    private var ivGraph: ImageView? = null
    private var noSensor: TextView? = null
    private var isSensorAvailable: Boolean = true
    private var lytMain: LinearLayout? = null
    override fun onCreated() {
        super.onCreated()
        layout = R.layout.sensoer_item
    }

    override fun render(view: View, card: Card) {
        super.render(view, card)
        val homeCardEntity = data as HomeCardEntity

        tvType = findViewById<TextView>(view, R.id.tvType, TextView::class.java!!) as TextView
        tvMfr = findViewById<TextView>(view, R.id.tvMfr, TextView::class.java!!) as TextView
        tvCons = findViewById<TextView>(view, R.id.tvCons, TextView::class.java!!) as TextView
        tvReso = findViewById<TextView>(view, R.id.tvReso, TextView::class.java!!) as TextView
        tvMinDelay = findViewById<TextView>(view, R.id.tvMinDelay, TextView::class.java!!) as TextView


        ivGraph = findViewById<ImageView>(view, R.id.ivGraph, ImageView::class.java!!) as ImageView
        xValue = findViewById<TextView>(view, R.id.tvx, TextView::class.java!!) as TextView
        title = findViewById<TextView>(view, R.id.title, TextView::class.java!!) as TextView
        yValue = findViewById<TextView>(view, R.id.tvy, TextView::class.java!!) as TextView
        zValue = findViewById<TextView>(view, R.id.tvz, TextView::class.java!!) as TextView
        noSensor = findViewById<TextView>(view, R.id.noSensor, TextView::class.java!!) as TextView
        lytMain = findViewById<LinearLayout>(view, R.id.lytMain, LinearLayout::class.java!!) as LinearLayout
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        title!!.text = homeCardEntity.title
        if (homeCardEntity.title.equals("Accelerometer")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {

                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Gyroscope")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Magnetometer")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Gravity Sensor")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_GRAVITY)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Linear Acceleration")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Rotation Sensor")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Ambient Temperature")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Proximity Sensor")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Light Sensor")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Pressure Sensor")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_PRESSURE)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        } else if (homeCardEntity.title.equals("Relative Sensor")) {
            lytMain!!.visibility = View.VISIBLE
            noSensor!!.visibility = View.GONE
            accelerometer = sensorManager!!.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
            if (accelerometer == null) {
                isSensorAvailable = false
                lytMain!!.visibility = View.GONE
                noSensor!!.visibility = View.VISIBLE
            } else {
                tvType!!.text = accelerometer!!.name
                tvMfr!!.text = accelerometer!!.vendor
                tvCons!!.text = accelerometer!!.power.toString()
                tvReso!!.text = accelerometer!!.resolution.toString()
                tvMinDelay!!.text = accelerometer!!.minDelay.toString()

            }
        }
        if (isSensorAvailable)
            sensorManager!!.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        ivGraph!!.setOnClickListener {

            val intent = Intent(context, SensorTestActivity::class.java)
            intent.putExtra("type", homeCardEntity.title)
            context.startActivity(intent)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_PRESSURE) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_LIGHT) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            getAccelerometer(event)
        } else if (event.sensor.type == Sensor.TYPE_GRAVITY) {
            getAccelerometer(event)
        }

    }

    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values
        val x = values[0]
        val y = values[1]
        val z = values[2]
        xValue!!.text = x.toString()
        yValue!!.text = y.toString()
        zValue!!.text = z.toString()

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

    }
}