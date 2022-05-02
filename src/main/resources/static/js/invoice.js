function calculateGrandTotal() {
    var total = Number(document.getElementById("total").value);
    var cgst = Number(document.getElementById("cgst").value);
    var sgst = Number(document.getElementById("sgst").value);

    var grandTotal = 0.0;

    grandTotal = total + total * cgst / 100 + total * sgst / 100;
    document.getElementById("grandTotal").value = grandTotal.toFixed(2);
}