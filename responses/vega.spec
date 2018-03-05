{
  "$schema": "https://vega.github.io/schema/vega/v3.json",
  "width": 500,
  "height": 200,
  "padding": 5,


  "data": [
    {
     "url": "http://127.0.0.1:8999/api/data",
     "name": "table"
    }
  ],

  "scales": [
    {
      "name": "x",
      "type": "point",
      "range": "width",
      "domain": {"data": "table", "field": "xname"}
    },
    {
      "name": "y",
      "type": "linear",
      "range": "height",
      "nice": true,
      "zero": true,
      "domain": {"data": "table", "field": "pvalue"}
    },
    {
      "name": "color",
      "type": "ordinal",
      "range": "category",
      "domain": {"data": "table", "field": "bucket"}
    }
  ],

  "axes": [
          {
            "orient": "bottom",
            "scale": "x",
            "title": "X-Axis",
            "encode": {
              "ticks": {
                "update": {
                  "stroke": {"value": "steelblue"}
                }
              },
              "labels": {
                "interactive": true,
                "update": {

                  "fill": {"value": "steelblue"},
                  "angle": {"value": 50},
                  "fontSize": {"value": 14},
                  "align": {"value": "left"},
                  "baseline": {"value": "middle"},
                  "dx": {"value": 3}
                },
                "hover": {
                  "fill": {"value": "firebrick"}
                }
              },
              "title": {
                "update": {
                  "fontSize": {"value": 16}
                }
              },
              "domain": {
                "update": {
                  "stroke": {"value": "#333"},
                  "strokeWidth": {"value": 1.5}
                }
              }
            }
          },
           { "orient": "left", "scale": "y" }
        ],

  "marks": [
    {
      "type": "group",
      "from": {
        "facet": {
          "name": "series",
          "data": "table",
          "groupby": "bucket"
        }
      },
      "marks": [
        {
          "type": "line",
          "from": {"data": "series"},
          "encode": {
            "enter": {
              "x": {"scale": "x", "field": "xname"},
              "y": {"scale": "y", "field": "pvalue"},
              "stroke": {"scale": "color", "field": "bucket"},
              "strokeWidth": {"value": 2}
            },

            "hover": {
              "fillOpacity": {"value": 0.5}
            }
          }
        }
      ]
    }
  ],

  "legends": [
    {
      "fill": "color",
      "encode": {
        "title": {
          "update": {
            "fontSize": {"value": 14}
          }
        },
        "labels": {
          "interactive": true,
          "update": {
            "fontSize": {"value": 12},
            "fill": {"value": "black"}
          },
          "hover": {
            "fill": {"value": "firebrick"}
          }
        },
        "symbols": {
          "update": {
            "stroke": {"value": "transparent"}
          }
        },
        "legend": {
          "update": {
            "stroke": {"value": "#ccc"},
            "strokeWidth": {"value": 1.5}
          }
        }
      }
    }
  ]
}