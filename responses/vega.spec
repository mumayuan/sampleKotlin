{
	"$schema": "https://vega.github.io/schema/vega/v3.json",
	"width": 200,
	"height": 200,

	"data": [{
		"url": "http://127.0.0.1:8999/api/data",
		"name": "table",
		"format": {"property": "hits.hits"}
	}],
"scales": [
    {
      "name": "xscale",
      "type": "point",
      "domain": {"data": "table", "field": "_source.created"},
      "range": "width"
    },
    {
      "name": "yscale",
      "domain": {"data": "table", "field": "_source.elapsedTime"},
      "nice": true,
      "range": "height"
    }
  ],
       "axes": [
    { "orient": "bottom", "scale": "xscale" },
    { "orient": "left", "scale": "yscale" }
  ],

	"marks": [
	{
      "type": "line",
      "from": {"data":"table"},
      "encode": {
        "enter": {
          "x": {"scale": "xscale", "field": "_source.created"},
          "y": {"scale": "yscale", "field": "_source.elapsedTime"}
        },
        "update": {
		
          "fillOpacity": {"value": "steelblue"}
        },
        "hover": {
          "fillOpacity": {"value": "red"}
        }
      }
    }
	]
}
