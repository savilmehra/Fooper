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

import android.view.Surface
import android.view.WindowManager
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
    private var mAccelerometer2: Sensor? = null
    private var mWindowManager: WindowManager?=null;
    val sinEntries = ArrayList<Entry>() // List to store data-points of sine curve
    val cosEntries = ArrayList<Entry>()
    val zAxies = ArrayList<Entry>()// List to store data-points of cosine curve
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.chart)
        mChart = findViewById<LineChart>(R.id.chart1)
        mWindowManager = getWindow().getWindowManager()
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
        mChart!!.setBackgroundColor(Color.BLACK)

        val data = LineData()
        data.setValueTextColor(Color.BLACK)

        val leftAxis = mChart!!.axisLeft
        val rights = mChart!!.axisRight
        rights.spaceMin=100f
        leftAxis.textColor = Color.WHITE
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawGridLines(true)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        type = intent.getStringExtra("type")
        if (type.equals("mAccelerometer")) {
            leftAxis.axisMinimum = -50f
            leftAxis.axisMaximum = 50f
            leftAxis.spaceMin=100f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        } else if (type.equals("Gyroscope"))
        {
            leftAxis.axisMinimum = -50f
            leftAxis.axisMaximum = 50f
            leftAxis.spaceMin=500f
            leftAxis.labelCount=4
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        }
        else if (type.equals("Magnetometer"))
        {
            leftAxis.axisMinimum = -100f
            leftAxis.axisMaximum = 100f
            leftAxis.spaceMin=50f
            leftAxis.labelCount=5
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        }
        else if (type.equals("Gravity Sensor"))
        {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            leftAxis.spaceMin=5f
            leftAxis.labelCount=5
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GRAVITY)
        }
        else if (type.equals("Linear Acceleration"))
        {
            leftAxis.axisMinimum = -20f
            leftAxis.axisMaximum = 20f
            leftAxis.spaceMax=5f
            leftAxis.labelCount=6
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        }
        else if (type.equals("Rotation Sensor"))
        {
            leftAxis.axisMinimum = -180f
            leftAxis.axisMaximum = 180f
            leftAxis.spaceMax=90f
            leftAxis.labelCount=3
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        }
        else if (type.equals("Ambient Temperature"))
        {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        }
        else if (type.equals("Proximity Sensor"))
        {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        }
        else if (type.equals("Light Sensor"))
        {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        }
        else if (type.equals("Pressure Sensor"))
        {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PRESSURE)
        }
        else if (type.equals("Relative Sensor"))
        {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        }
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        val sensors = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)

        for (i in sensors.indices) {
            Log.d(TAG, "onCreate: Sensor " + i + ": " + sensors[i].toString())
        }

        if (mmAccelerometer != null) {
            mSensorManager!!.registerListener(this, mmAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }


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

        val rightAxis = mChart!!.axisRight
        rightAxis.isEnabled = false

        mChart!!.axisLeft.setDrawGridLines(true)
        mChart!!.xAxis.setDrawGridLines(true)
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

            zaxies.lineWidth = 0.5f
            zaxies.color = Color.RED
            zaxies.isHighlightEnabled = false
            zaxies.setDrawValues(false)
            zaxies.setDrawCircles(false)
            zaxies.mode = LineDataSet.Mode.CUBIC_BEZIER
            zaxies.cubicIntensity = 0.2f
// Adding colors to different plots

            cosSet.lineWidth = 0.5f
            cosSet.color = Color.GREEN
            cosSet.isHighlightEnabled = false
            cosSet.setDrawValues(false)
            cosSet.setDrawCircles(false)
            cosSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            cosSet.cubicIntensity = 0.2f

            sinSet.lineWidth = 0.5f
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
            mChart!!.setVisibleXRangeMaximum(200f)
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
            } else if (event.sensor.type == Sensor.TYPE_ORIENTATION) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
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

    private fun updateOrientation(event: SensorEvent) {
        val aX = event.values[1]
        val aY = event.values[2]

        val angley= aY * (Math.PI / 180).toFloat()
        val anglex = aX * (Math.PI / 180).toFloat()
        val yaw = event.values[0]
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
            data.addEntry(Entry(set.entryCount.toFloat(), anglex), 0)
            data.notifyDataChanged()
            sinEntries.add(Entry(set.entryCount.toFloat(), angley))
            cosEntries.add(Entry(set.entryCount.toFloat(), anglex))
            zAxies.add(Entry(set.entryCount.toFloat(), yaw))
            val dataSets = ArrayList<ILineDataSet>() // for adding multiple plots

            val sinSet = LineDataSet(sinEntries, "pitch")
            val cosSet = LineDataSet(cosEntries, "roll")
            val zaxies = LineDataSet(zAxies, "yaw")

            zaxies.lineWidth = 0.5f
            zaxies.color = Color.RED
            zaxies.isHighlightEnabled = false
            zaxies.setDrawValues(false)
            zaxies.setDrawCircles(false)
            zaxies.mode = LineDataSet.Mode.CUBIC_BEZIER
            zaxies.cubicIntensity = 0.2f
// Adding colors to different plots

            cosSet.lineWidth = 0.5f
            cosSet.color = Color.GREEN
            cosSet.isHighlightEnabled = false
            cosSet.setDrawValues(false)
            cosSet.setDrawCircles(false)
            cosSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            cosSet.cubicIntensity = 0.2f

            sinSet.lineWidth = 0.5f
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
            mChart!!.setVisibleXRangeMaximum(200f)
            // mChart.setVisibleYRange(30, AxisDependency.LEFT);

            // move to the latest entry
            mChart!!.moveViewToX(data.entryCount.toFloat())

        }
    }
}