$(function () {
    echart_map();
    echart_map_bar();

    //中国地图
    function echart_map() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_map'),);

        var mapName = 'china'
        var data = []
        // var toolTipData = [];

        option = {
            tooltip: {
                trigger: 'item'
            },
            title: {
                text: '中国新冠肺炎累计确诊数据',
                subtext: '人口密度数据来自官方疫情通报'+'\n'+'\n可用鼠标放大 缩小 移动',
                sublink: 'http://m.sinovision.net/newpneumonia.php',
                left: 'center',
                align: 'right',
                top: '15%',
                backgroundColor: 'Gray',
                borderColor: 'white'
            },
            // 鼠标移动弹窗
            tooltip: {
                trigger: 'item',
                formatter: '{b}<br/>{c} (人)'
            },
            //右边数据视图等
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: { readOnly: false },
                    restore: {},
                    saveAsImage: {}
                }
            },
            //左侧颜色边框+颜色条
            visualMap: {
                min: 0,
                max: 1000,
                text: ['High', 'Low'],
                realtime: false,
                calculable: true,
                inRange: {
                    color: ['white', '#FFA07A', '#F08080','#DC143C', '#B22222']
                }
            },

            series: [
                {
                    type: "map",
                    mapType: "china",
                    roam: true,
                    label: {
                        fontFamily: 'Microsoft YaHei',
                        show: true,
                        color: 'black',
                        align: 'center',
                        position: 'inside'
                    },
                    zoom:1.2,
                    itemStyle: {
                        borderType: 'dotted',
                        emphasis: {// 也是选中样式
                            // areaColor: 'red',
                            areaColor: '#7B68EE',
                            
                            shadowColor: '#9370DB',
                            shadowBlur: 20,
                            label: {
                                show: true,
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                         }    
                    },
                    data: [],
                }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        /*获取地图数据*/
        myChart.showLoading();
        var mapFeatures = echarts.getMap(mapName).geoJson.features;
        // myChart.hideLoading();
        // 获取json
        var names = []
        var nums = []
        $.ajax({
            type: 'get',
            url: 'http://127.0.0.1:8887/data/china',
            dataType: 'json',
            success: function (result) {
                console.log(result);
                var dataList = []

                $.each(result.RECORDS, function (index, item) {
                    // alert(index + item.country + item.confirm);
                    dataList.push({ name: item.name, value: item.confirm })
                    // names.push(item.country);
                    // nums.push(item.confirm);
                });

                myChart.hideLoading();
                myChart.setOption({
                    series: [{
                        data: dataList
                        // data:[{
                        //     name:names,
                        //     value:nums
                        // }]
                    }]

                });

            },
            error: function (errorMessage) {
                alert("Data is Error.")
                myChart.hideLoading();
            }
        });

        window.addEventListener("resize", function () {
            myChart.resize();
        });

    }
    //中国饼图
    function echart_map_bar() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_map_bar'),);
        var mapName = 'world'
        var data = []

        var option = {
            title: {
                text: '中国各省现有确诊数据',
                subtext: '统计数据来自猫眼电影',
                // sublink: 'https://maoyan.com/',
                left: 'center',
                align: 'right',
                top: '15%',
                backgroundColor: 'Gray',
                borderColor: 'white'
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                },
                formatter: '现确诊数：{b}<br/>{c} (人)'
            },
            legend: {
                data:['现确诊数']
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            dataZoom: [
                {
                    show: true,
                    realtime: true,
                    start: 0,
                    end: 1
                },
                {
                    type: 'inside',
                    realtime: true,
                    start: 65,
                    end: 85
                },
                {
                    show: true,
                    yAxisIndex: 0,
                    filterMode: 'empty',
                    width: 30,
                    height: '80%',
                    showDataShadow: false,
                    left: '93%'
                }
            ],
            yAxis: {
                // type:'catagory',
                axisLabel: {
                    interval: 0,
                    rotate: 30,
                    color:'#E6E6FA'
                },
                color:'blue',
                name:'',
                data: []
            },
            xAxis: {
                name:'现确诊数/人',
                axisLabel: {
                    color:'#E6E6FA'
                },
            },
            series: [{
                center:[300,200],
                name: '现确诊数',
                type: 'bar',
                roundCap: true,
                color: '#2f4554',
                itemStyle: {
                    borderColor: 'skyblue',
                    borderWidth: 1
                },
                label: {
                    normal: {
                        position: 'right',
                        show: true
                    }
                },
                data: []
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.ajax({
            type: 'get',
            url: 'http://127.0.0.1:8887/data/china',
            dataType: 'json',
            success: function (result) {
                var names=[]
                var nums=[]
                var dataList = []

                $.each(result.RECORDS, function (index, item) {
                    // alert(index + item.name + item.confirm);
                    // dataList.push({ name: item.name, value: item.confirm })
                    names.push(item.name);
                    nums.push(item.confirm-item.cure);
                });

                myChart.hideLoading();
                myChart.setOption({
                    yAxis:{
                        data:names,
                    },
                    series: [{
                        data: nums,
                        // data:[{
                        //     name:names,
                        //     value:nums
                        // }]
                    }]

                });

            },
            error: function (errorMessage) {
                alert("Data is Error.")
                myChart.hideLoading();
            }
        });

        window.addEventListener("resize", function () {
            myChart.resize();
        });
    }

    //世界地图
    function echart_world() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_world'),);

        var mapName = 'world'
        var data = []
        // var toolTipData = [];

        option = {
            tooltip: {
                trigger: 'item'
            },
            title: {
                text: '世界新冠肺炎疫情累计确诊数据',
                subtext: '人口密度数据来自官方疫情通报'+'\n'+'\n可用鼠标放大 缩小 移动',
                sublink: 'http://m.sinovision.net/newpneumonia.php',
                left: 'center',
                align: 'right',
                top: '15%',
                backgroundColor: 'Gray',
                borderColor: 'white'
            },
            // 鼠标移动弹窗
            tooltip: {
                trigger: 'item',
                formatter: '{b}<br/>{c} (人)'
            },
            //右边数据视图等
            toolbox: {
                show: true,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                    dataView: { readOnly: false },
                    restore: {},
                    saveAsImage: {}
                }
            },
            // //左侧颜色边框+颜色条
            // visualMap: {
            //     min: 0,
            //     max: 100000,
            //     text: ['High', 'Low'],
            //     realtime: false,
            //     calculable: true,
            //     // inRange: {
            //     //     color: ['white', 'yellow', 'orangered', 'red']
            //     // }
            // },
            dataRange: {
                x: 'left',
                y: 'bottom',
                splitList: [
                    { start: 100000, color: '#800000' },
                    { start: 10000, end: 99999, color: '#B22222' },
                    { start: 1000, end: 9999, color: '#DC143C' },
                    { start: 100, end: 999, color: '#FF6347' },
                    { start: 50, end: 99, color: '#F08080' },
                    { start: 10, end: 49, color: '#FFA07A' },
                    { start: 1, end: 9, color: '#FFDAB9' },
                    { end: 0, color: 'white' }
                ],
                // color: ['#800000','#EEE8AA', '#DC143C','#FF6347', '#F08080','#FFA07A','#FFDAB9']
            },

            series: [
                {
                    type: "map",
                    mapType: "world",
                    roam: true,
                    label: {
                        fontFamily: 'Microsoft YaHei',
                        show: false,
                        color: 'black',
                        align: 'center',
                        position: 'inside'
                    },
                    itemStyle: {
                        borderColor: 'DimGray',
                        borderType: 'dotted',
                        emphasis: {// 也是选中样式
                            // areaColor: 'red',
                            areaColor: '#7B68EE',
                            
                            shadowColor: '#9370DB',
                            shadowBlur: 20,
                            label: {
                                show: true,
                                textStyle: {
                                    color: '#fff'
                                }
                            }
                         }    
                    },
                    data: [],
                    zoom:1.5,

                    nameMap: {
                        'United States': 'US',
                        'China': 'Mainland China',
                        'Korea': 'South Korea',
                        'Western Sahara': 'W.Sahara',
                        'United Kingdom': 'UK',
                        'S. Sudan': 'Sudan',
                        'Dem. Rep. Congo': 'Congo',
                        'Lao PDR': 'Laos',
                        "CA`te d'lvoire": 'Ivory Coast',
                    }
                }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        /*获取地图数据*/
        myChart.showLoading();
        var mapFeatures = echarts.getMap(mapName).geoJson.features;
        myChart.hideLoading();
        // 获取json
        var names = []
        var nums = []
        $.ajax({
            type: 'get',
            url: 'http://127.0.0.1:8887/data/world',
            dataType: 'json',
            success: function (result) {

                var dataList = []

                $.each(result.RECORDS, function (index, item) {
                    // alert(index + item.country + item.confirm);
                    dataList.push({ name: item.country, value: item.confirm })
                    // names.push(item.country);
                    // nums.push(item.confirm);
                });

                myChart.hideLoading();
                myChart.setOption({
                    series: [{
                        data: dataList
                        // data:[{
                        //     name:names,
                        //     value:nums
                        // }]
                    }]

                });

            },
            error: function (errorMessage) {
                alert("Data is Error.")
                myChart.hideLoading();
            }
        });

        window.addEventListener("resize", function () {
            myChart.resize();
        });

    }
    
    function echart_world_bar() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('chart_world_bar'),);

        var mapName = 'world'
        var data = []
        // var toolTipData = [];

        option = {
            title: {
                text: '全球各国新冠肺炎累计治愈数据',
                subtext: '\n可用鼠标放大 缩小 移动',
                left: 'center',
                align: 'right',
                top: '15%',
                backgroundColor: 'Gray',
                borderColor: 'white'
            },
            toolbox: {
                show: true,
                feature: {
                    mark: {show: true},
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            xAxis: {
                data:[],
                axisLabel: {
                    textStyle: {
                        color: '#03a9f4'
                    }
                },
                splitLine: {
                    show: false
                }
            },
            yAxis: {
                type: 'value',
                name: '治愈人数 (人)',
                axisLine: {
                    show: false
                }
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend:{data:'治愈数'},
            dataZoom: [
                {
                    show: true,
                    start: 94,
                    end: 100
                },
                {
                    type: 'inside',
                    start: 94,
                    end: 100
                },
                {
                    show: true,
                    yAxisIndex: 0,
                    filterMode: 'empty',
                    width: 30,
                    height: '80%',
                    showDataShadow: false,
                    left: '93%'
                }
            ],
            series: [ 
                {
                    name:"治愈数/人",
                    type: 'bar',
                    data: [],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ],
                        color:'white',
                    },
                    grid: {
                        x: '30%',
                        top: '10px',
                        bottom: '15px',
                        right: '40%'
                    },
                    barWidth: 20,
                    itemStyle: {
                        normal: {
                            barBorderRadius: 20,
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#4169E1'
                            }, {
                                offset: 1,
                                color: '#87CEFA'
                            }]),
                            shadowColor: '#7B68EE',
                            shadowBlur: 20
                        }
                    }
                }
            ]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        /*获取地图数据*/
        myChart.showLoading();
        var mapFeatures = echarts.getMap(mapName).geoJson.features;
        myChart.hideLoading();
        // 获取json
        var names = []
        var nums = []
        $.ajax({
            type: 'get',
            url: 'http://127.0.0.1:8887/data/world',
            dataType: 'json',
            success: function (result) {

                var dataList = []

                $.each(result.RECORDS, function (index, item) {
                    // alert(index + item.country + item.confirm);
                    // dataList.push({ name: item.country, value: item.confirm })
                    names.push(item.country);
                    nums.push(item.cure);
                });

                myChart.hideLoading();
                myChart.setOption({
                    xAxis:{
                        data:names,
                    },
                    series: [{
                        data: nums
                        // data:[{
                        //     name:names,
                        //     value:nums
                        // }]
                    }]

                });

            },
            error: function (errorMessage) {
                alert("Date is Error.")
                myChart.hideLoading();
            }
        });

        window.addEventListener("resize", function () {
            myChart.resize();
        });

    }

    //操作按钮
    $('.t_btn0').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos0').css('display', 'block');
        echart_map();
        echart_map_bar()
        // echart_3();
    });
    $('.t_btn3').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos3').css('display', 'block');
        echart_world();
        echart_world_bar();
    });


    $('.t_btn1').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos1').css('display', 'block');
        echart_2();
    });
    $('.t_btn2').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos2').css('display', 'block');
        echart_0();
    });

    $('.t_btn4').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos6').css('display', 'block');
        echart_6();
    });
    $('.t_btn5').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos4').css('display', 'block');
        echart_1();
    });
    $('.t_btn6').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos5').css('display', 'block');
        echart_3();
    });
    $('.t_btn7').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos7').css('display', 'block');
        echart_7();
    });
    $('.t_btn8').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos8').css('display', 'block');
        echart_8();
    });
    $('.t_btn9').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos9').css('display', 'block');
        echart_9();
    });
    $('.t_btn10').click(function () {
        $('.center_text').css('display', 'none');
        $('.t_cos10').css('display', 'block');
        echart_10();
    });

    //获取地址栏参数
    $(function () {
        function getUrlParms(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = window.location.search.substr(1).match(reg);
            if (r != null)
                return unescape(r[2]);
            return null;
        }
        var id = getUrlParms("id");
        if (id == 2) {
            $('.center_text').css('display', 'none');
            $('.t_cos10').css('display', 'block');
            echart_10();
        }
        if (id == 3) {
            $('.center_text').css('display', 'none');
            $('.t_cos11').css('display', 'block');
            echart_11();
        }
        if (id == 4) {
            $('.center_text').css('display', 'none');
            $('.t_cos1').css('display', 'block');
            echart_2();
        }
        if (id == 5) {
            $('.center_text').css('display', 'none');
            $('.t_cos6').css('display', 'block');
            echart_6();
        }
        if (id == 6) {
            $('.center_text').css('display', 'none');
            $('.t_cos4').css('display', 'block');
            echart_1();
        }
        if (id == 7) {
            $('.center_text').css('display', 'none');
            $('.t_cos8').css('display', 'block');
            echart_8();
        }
        if (id == 8) {
            $('.center_text').css('display', 'none');
            $('.t_cos12').css('display', 'block');
            echart_12();
        }
        if (id == 9) {
            $('.center_text').css('display', 'none');
            $('.t_cos13').css('display', 'block');
            echart_13();
        }
    });
});
