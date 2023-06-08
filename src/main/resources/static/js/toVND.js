// Lấy danh sách tất cả các phần tử HTML có class là "product_price"
const prices = document.querySelectorAll('.product_price');

// Lặp qua danh sách các phần tử và định dạng giá trị hiển thị của chúng
prices.forEach(price => {

  // Kiểm tra nếu giá sản phẩm bằng null thì gán giá trị mặc định "Giá sản phẩm chưa cập nhật"
  let formattedPrice;
  if (price.textContent === "0.0" ) {
    formattedPrice = "Giá sản phẩm chưa cập nhật";
  } else {
    // Định dạng giá trị hiển thị của phần tử bằng cách sử dụng hàm Intl.NumberFormat()
    formattedPrice = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price.textContent);
  }
  // Gán giá trị định dạng cho phần tử đó bằng cách sử dụng textContent
  price.textContent = formattedPrice;
});
