

//define some sample data
var tabledata = [
    {id:1, name:"Oli Bob", age:"12", col:"red", dob:""},
    {id:2, name:"Mary May", age:"1", col:"blue", dob:"14/05/1982"},
    {id:3, name:"Christine Lobowski", age:"42", col:"green", dob:"22/05/1982"},
    {id:4, name:"Brendon Philips", age:"125", col:"orange", dob:"01/08/1980"},
    {id:5, name:"Margret Marmajuke", age:"16", col:"yellow", dob:"31/01/1999"},
    {id:1, name:"Billy Bob", age:"12", gender:"male", col:"red", dob:""},
    {id:2, name:"Mary May", age:"1", gender:"female", col:"blue", dob:"14/05/1982"},
    {id:3, name:"Christine Lobowski", age:"42", col:"green", dob:"22/05/1982"},
    {id:4, name:"Brendon Philips", age:"125", gender:"male", col:"orange", dob:"01/08/1980"},
    {id:5, name:"Margret Marmajuke", age:"16", gender:"female", col:"yellow", dob:"31/01/1999"},
    {id:6, name:"Billy Bob", age:"12", gender:"male", col:"red", dob:""},
    {id:7, name:"Mary May", age:"1", gender:"female", col:"blue", dob:"14/05/1982"},
    {id:8, name:"Christine Lobowski", age:"42", col:"green", dob:"22/05/1982"},
    {id:9, name:"Brendon Philips", age:"125", gender:"male", col:"orange", dob:"01/08/1980"},
    {id:10, name:"Margret Marmajuke", age:"16", gender:"female", col:"yellow", dob:"31/01/1999"},
];

let aTable = new Tabulator("#example-table", {
 	data:tabledata, //assign data to table
    pagination:"local",
    paginationSize:12,
 	layout:"fitColumns", //fit columns to width of table (optional)
 	columns:[ //Define Table Columns
	 	{title:"Name", field:"name", width:150},
	 	{title:"Age", field:"age", align:"left", formatter:"progress"},
	 	{title:"Favourite Color", field:"col"},
	 	{title:"Date Of Birth", field:"dob", sorter:"date", align:"center"},
 	],
 	rowClick:function(e, row){ //trigger an alert message when the row is clicked
 		alert("Row " + row.getData().id + " Clicked!!!!");
 	},
});

aTable.setData(tabledata);

var newData = [{id:6, name:"Phil", age:"16", col:"yellow", dob:"31/01/1999"}];
$("#example-table").tabulator("updateOrAdd", newData, true);