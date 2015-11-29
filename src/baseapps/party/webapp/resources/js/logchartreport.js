/**
 * Unicorn Admin Template
 * Diablo9983 -> diablo9983@gmail.com
 **/
$(document).ready(function(){

    var data = [];
    var datalabel = [];
    var series = 5;//Math.floor(Math.random()*10)+1;
    datalabel[0]="信息";
    datalabel[1]="警告";
    datalabel[2]="错误";
    datalabel[3]="严重错误";
    datalabel[4]="致命错误";

    for( var i = 0; i<series; i++)
    {
        data[i] = { label: datalabel[i], data: Math.floor(Math.random()*100)+1 }
    }

    var pie = $.plot($(".pie"), data,{
        series: {
            pie: {
                show: true,
                radius: 3/4,
                label: {
                    show: true,
                    radius: 3/4,
                    formatter: function(label, series){
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'+label+'<br/>'+Math.round(series.percent)+'%</div>';
                    },
                    background: {
                        opacity: 0.5,
                        color: '#000'
                    }
                },
                innerRadius: 0.2
            },
            legend: {
                show: false
            }
        }
    });
   /*
    var d1 = [];
    for (var i = 0; i <= 10; i += 1) d1.push([i, parseInt(Math.random() * 30)]);

    var data = new Array();
    data.push({
        data:d1,
        bars: {
            show: true,
            barWidth: 0.4,
            order: 1,
        }
    });
    //Display graph
    var bar = $.plot($(".bars"), data, {
        legend: true
    });
    */
});


unicorn = {
    // === Tooltip for flot charts === //
    flot_tooltip: function(x, y, contents) {

        $('<div id="tooltip">' + contents + '</div>').css( {
            top: y + 5,
            left: x + 5
        }).appendTo("body").fadeIn(200);
    }
}
