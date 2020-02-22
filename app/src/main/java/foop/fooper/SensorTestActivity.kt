package foop.fooper

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


class SensorTestActivity : AppCompatActivity(), SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private var mmAccelerometer: Sensor? = null
    private val sensors: Sensor? = null
    private var mChart: LineChart? = null
    private var thread: Thread? = null
    private var plotData = true
    private var type: String? = null
    private var mAccelerometer: Sensor? = null
    val sinEntries = ArrayList<Entry>() // List to store data-points of sine curve
    val cosEntries = ArrayList<Entry>()
    val zAxies = ArrayList<Entry>()// List to store data-points of cosine curve
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chart)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        type = intent.getStringExtra("type")
        if (type.equals("mAccelerometer"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        else if (type.equals("Gyroscope"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        else if (type.equals("Magnetometer"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        else if (type.equals("Gravity Sensor"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GRAVITY)
        else if (type.equals("Linear Acceleration"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        else if (type.equals("Rotation Sensor"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        else if (type.equals("Ambient Temperature"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        else if (type.equals("Proximity Sensor"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        else if (type.equals("Light Sensor"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        else if (type.equals("Pressure Sensor"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PRESSURE)
        else if (type.equals("Relative Sensor"))
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
            mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
       
        val sensors = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)

        for (i in sensors.indices) {
            Log.d(TAG, "onCreate: Sensor " + i + ": " + sensors[i].toString())
        }

        if (mmAccelerometer != null) {
            mSensorManager!!.registerListener(this, mmAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }

        mChart = findViewById<LineChart>(R.id.chart1)

        // enable description text
        mChart!!.description.isEnabled = true

        // enable touch gestures
        mChart!!.setTouchEnabled(true)

        // enable scaling and dragging
        mChart!!.isDragEnabled = true
        mChart!!.setScaleEnabled(true)
        mChart!!.setDrawGridBackground(false)

        // if disabled, scaling can be done on x- and y-axis separately
        mChart!!.setPinchZoom(true)

        // set an alternative background color
        mChart!!.setBackgroundColor(Color.DKGRAY)

        val data = LineData()
        data.setValueTextColor(Color.BLACK)

        // add empty data
        mChart!!.data = data

        // get the legend (only possible after setting data)
        val l = mChart!!.legend

        // modify the legend ...
        l.form = Legend.LegendForm.LINE
        l.textColor = Color.WHITE

        val xl = mChart!!.xAxis
        xl.textColor = Color.WHITE
        xl.setDrawGridLines(true)
        xl.setAvoidFirstLastClipping(true)
        xl.isEnabled = true

        val leftAxis = mChart!!.axisLeft
        leftAxis.textColor = Color.WHITE
        leftAxis.setDrawGridLines(false)
        leftAxis.axisMaximum = 100f
        leftAxis.axisMinimum = -100f
        leftAxis.setDrawGridLines(true)

        val rightAxis = mChart!!.axisRight
        rightAxis.isEnabled = false

        mChart!!.axisLeft.setDrawGridLines(false)
        mChart!!.xAxis.setDrawGridLines(false)
        mChart!!.setDrawBorders(false)

        feedMultiple()

    }

    private fun addEntry(event: SensorEvent) {

        val data = mChart!!.data


        if (data != null) {
            val chartData = LineData()
            var set: ILineDataSet? = data.getDataSetByIndex(0)
            var set2: ILineDataSet? = data.getDataSetByIndex(0)
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet()
                data.addDataSet(set)
            }
            if (set2 == null) {
                set2 = createSet()
                data.addDataSet(set2)
            }

            //data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 80) + 10f), 0);
            data.addEntry(Entry(set.entryCount.toFloat(), event.values[0]), 0)
            data.notifyDataChanged()
            sinEntries.add(Entry(set.entryCount.toFloat(), event.values[0]))
            cosEntries.add(Entry(set.entryCount.toFloat(), event.values[1]))
            zAxies.add(Entry(set.entryCount.toFloat(), event.values[2]))
            val dataSets = ArrayList<ILineDataSet>() // for adding multiple plots

            val sinSet = LineDataSet(sinEntries, "X")
            val cosSet = LineDataSet(cosEntries, "Y")
            val zaxies = LineDataSet(zAxies, "Z")

            zaxies.lineWidth = 3f
            zaxies.color = Color.RED
            zaxies.isHighlightEnabled = false
            zaxies.setDrawValues(false)
            zaxies.setDrawCircles(false)
            zaxies.mode = LineDataSet.Mode.CUBIC_BEZIER
            zaxies.cubicIntensity = 0.2f
// Adding colors to different plots

            cosSet.lineWidth = 3f
            cosSet.color = Color.GREEN
            cosSet.isHighlightEnabled = false
            cosSet.setDrawValues(false)
            cosSet.setDrawCircles(false)
            cosSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            cosSet.cubicIntensity = 0.2f

            sinSet.lineWidth = 3f
            sinSet.color = Color.YELLOW
            sinSet.isHighlightEnabled = false
            sinSet.setDrawValues(false)
            sinSet.setDrawCircles(false)
            sinSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            sinSet.cubicIntensity = 0.2f
            dataSets.add(sinSet)
            dataSets.add(cosSet)
            dataSets.add(zaxies)
            mChart!!.setData(LineData(dataSets))
            // let the chart know it's data has changed
            mChart!!.notifyDataSetChanged()

            // limit the number of visible entries
            mChart!!.setVisibleXRangeMaximum(150f)
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart!!.moveViewToX(data.entryCount.toFloat())

        }
    }

    private fun createSet(): LineDataSet {

        val set = LineDataSet(null, "Dynamic Data")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.lineWidth = 3f
        set.color = Color.MAGENTA
        set.isHighlightEnabled = false
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        return set
    }

    private fun createSet2(): LineDataSet {

        val set = LineDataSet(null, "Dynamic Data")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.lineWidth = 3f
        set.color = Color.GREEN
        set.isHighlightEnabled = false
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        return set
    }

    private fun feedMultiple() {

        if (thread != null) {
            thread!!.interrupt()
        }

        thread = Thread(Runnable {
            while (true) {
                plotData = true
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }

            }
        })

        thread!!.start()
    }

    override fun onPause() {
        super.onPause()

        if (thread != null) {
            thread!!.interrupt()
        }
        mSensorManager!!.unregisterListener(this)

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (plotData) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                addEntry(event)
                plotData = false
            }
            else if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
                addEntry(event)
                plotData = false
            }
            else if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_PRESSURE) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_LIGHT) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_GRAVITY) {
                addEntry(event)
                plotData = false
            }

        }
    }


    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mmAccelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onDestroy() {
        mSensorManager!!.unregisterListener(this@SensorTestActivity)
        thread!!.interrupt()
        super.onDestroy()
    }

    companion object {
        private val TAG = "SensorTestActivity"
    }
}