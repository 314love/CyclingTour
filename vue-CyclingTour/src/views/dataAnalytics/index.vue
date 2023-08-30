<template>
  <div>
    <div id="pie" :style="{ float: 'left', width: '100%', height: '600px' }" ref="mychart"/>
    <div style="height: 700px"></div>
    <div id="line" :style="{ float: 'left', width: '100%', height: '600px' }" ref="linechat"/>
    <div style="height: 700px"></div>
    <div id="bar" :style="{ float: 'left', width: '100%', height: '600px' }" ref="barchat"/>
  </div>

</template>

<script>
import * as echarts from "echarts";
import tripApi from '@/api/tripManage.js'

export default {
  data() {
    return {};
  },
  mounted() {
    this.initEcharts();
  },
  methods: {
    initEcharts() {
      const optionBar = {
        legend: {
          top: 'top'
        },
        title: {
          text: '路段对评分和人数的影响柱状图'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [],

        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: "路段对应评分柱状图",
            data: [],
            type: 'bar'
          },
          {
            name: "路段对应人数柱状图",
            data: [],
            type: 'bar'
          },
        ],
        dataZoom: [
          {
            type: 'slider', // 类型为滑动条
            show: true,     // 显示数据缩略轴
            xAxisIndex: 0, // 指定关联的 x 轴
            start: 0,       // 默认开始位置
            end: 100        // 默认结束位置
          }
        ]


      };
      const optionLine = {
        legend: {
          top: 'top'
        },
        title: {
          text: '路段对评分和人数的影响折线图'
        },
        tooltip: {
          trigger: 'axis'
        },
        xAxis: {
          type: 'category',
          data: [],

        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            name: "路段对应评分折线图",
            data: [],
            type: 'line'
          },
          {
            name: "路段对应人数折线图",
            data: [],
            type: 'line'
          },
        ],
        dataZoom: [
          {
            type: 'slider', // 类型为滑动条
            show: true,     // 显示数据缩略轴
            xAxisIndex: 0, // 指定关联的 x 轴
            start: 0,       // 默认开始位置
            end: 100        // 默认结束位置
          }
        ]

      };


      const option = {
        title: {
          text: '行程路段评分南丁格尔玫瑰图',
          subtext: '不同路段评分的可视化呈现',
          left: 'center'
        },
        legend: {
          top: 'bottom'
        },
        tooltip: {
          trigger: 'item'
        },
        toolbox: {
          show: true,

          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: '行程路段与评分',
            type: 'pie',
            radius: [50, 250],
            center: ['50%', '50%'],
            roseType: 'area',
            itemStyle: {
              borderRadius: 8
            },
            data: []
          }
        ]
      };

      tripApi.listTwo().then(res => {
        // 柱状图
        let data = res.data.map(v => v.routeName);
        optionBar.xAxis.data = data

        let data2 = res.data.map(v => v.routeRating);
        optionBar.series[0].data = data2

        let data3 = res.data.map(v => v.riderCount);
        optionBar.series[1].data = data3
        barChart.setOption(optionBar);// 渲染页面
        // 折线图
        let map = res.data.map(v => v.routeName);
        optionLine.xAxis.data = map
        console.log(map)

        let map2 = res.data.map(v => v.routeRating);
        optionLine.series[0].data = map2
        console.log(map2)

        let map3 = res.data.map(v => v.riderCount);
        optionLine.series[1].data = map3
        console.log(map3)
        lineChart.setOption(optionLine);// 渲染页面
        // 饼图
        // console.log(res)
        res.data.forEach(item => {
          option.series[0].data.push({
            name: item.routeName, value: item.routeRating
          })
        })
        // console.log(option)
        myChart.setOption(option);// 渲染页面

      })
      const myChart = echarts.init(this.$refs.mychart);// 图标初始化
      const lineChart = echarts.init(this.$refs.linechat);// 图标初始化
      const barChart = echarts.init(this.$refs.barchat);// 图标初始化


      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
      window.addEventListener("resize", () => {
        lineChart.resize();
      });
      window.addEventListener("resize", () => {
        barChart.resize();
      });
    }
  }
};
</script>

