<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>   
<!DOCTYPE html>
<html>

<head>
  <!-- Basic -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <!-- Mobile Metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <!-- Site Metas -->
  <meta name="keywords" content="" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <title>Urotaxi</title>


  <!-- slider stylesheet -->
  <link rel="stylesheet" type="text/css" href="../../https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

  <!-- bootstrap core css -->
  <link rel="stylesheet" type="text/css" href="../../css/bootstrap.css" />

  <!-- fonts style -->
  <link href="../../https://fonts.googleapis.com/css?family=Poppins:400,700&display=swap" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../../css/style.css" rel="stylesheet" />
  <!-- responsive style -->
  <link href="../../css/responsive.css" rel="stylesheet" />
</head>

<body class="sub_page">

  <div class="hero_area">
    <!-- header section strats -->
    <header class="header_section">
      <div class="container-fluid">
        <nav class="navbar navbar-expand-lg custom_nav-container ">
          <a class="navbar-brand" href="/">
            <span style="padding-bottom: 6px;">
              Urotaxi
            </span>
          </a>
          </a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="d-flex ml-auto flex-column flex-lg-row align-items-center">
              <ul class="navbar-nav  ">
               
                <!-- <li class="nav-item">
                  <a class="nav-link" href="customer.html">Customer</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="driver.html">Driver</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="tripbooking.html">TripBooking</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="cab.html">Cab</a>
                </li> -->

                <li class="nav-item ">
                  <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="../../about.html"> About</a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="../../service.html"> Services </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="../../news.html"> News</a>
                </li>
                <li class="nav-item active">
                  <a class="nav-link" href="../../contact.html">Contact Us</a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </header>
    <!-- end header section -->
  </div>  
  <div class="container" style= "background-color:white; margin: auto; padding-top: 50px; width:600px; margin-bottom: 50px;">
    <form:form action="addtripprocess" modelAttribute="trip" method="post"> 
       
    <div class="form-group">
     
      <form:hidden path="fromLocation" 
        class="form-control" name="" id="fromLocation"/>
    </div>
    <div class="form-group">
        <form:hidden path="toLocation" 
          class="form-control" name="" id="toLocation"/>
      </div>
      <div class="form-group">
        <form:hidden path="fromDateTime" 
          class="form-control" name="" id="fromDateTime"/>
      </div>
      <div class="form-group">
          <form:hidden path="toDateTime" 
            class="form-control" name="" id="toDateTime"/>
        </div>
        <form:hidden path="status" value="${trip.status}"/>
          <div class="form-group">
              <form:hidden path="distanceInKm" 
                class="form-control" name="" id="distanceInKm"/>
            </div>
            <div class="form-group">
                <form:hidden path="bill" 
                  class="form-control" name="" id="bill"/>
              </div>
              <div class="form-group">
                  <form:hidden path="driverId" 
                    class="form-control" name="" id="driver"/>
                </div>
                <div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">Driver Name : </h5>
                    <h5 class="mx-3">${trip.driverId.userName}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">Cab Type Location: </h5>
                    <h5 class="mx-3">${trip.driverId.cab.carType}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">Pick Up Location: </h5>
                    <h5 class="mx-3">${trip.fromLocation}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">Drop Location: </h5>
                    <h5 class="mx-3">${trip.toLocation}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">From Date Time: </h5>
                    <h5 class="mx-3">${trip.fromDateTime}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">To Date Time: </h5>
                    <h5 class="mx-3">${trip.toDateTime}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">Distance(inKm) : </h5>
                    <h5 class="mx-3">${trip.distanceInKm}</h5>
                  </div>
                  <div class="form-group d-flex justify-content-between">
                    <h5 class="mx-3">Charges : </h5>
                    <h5 class="mx-3">${trip.bill}</h5>
                  </div>
                </div>
      <div class="form-group">
        <button type="submit" class="btn" style= "background-color:#ffc107;">Book</button>
      </div>
</form:form>
<a href="../../../customer.html"><button class="btn" style="background-color: #ffc107; "><span>&#8592</span> Go Back</button></a>

</div>
  <section  class="info_section layout_padding-top layout_padding2-bottom">
    <div  class="container" >
      <div class="box">
        <div class="info_form">
          <h4>
            Subscribe Our Newsletter
          </h4>
          <form action="">
            <input type="text" placeholder="Enter your email">
            <div class="d-flex justify-content-end">
              <button>

              </button>
            </div>
          </form>
        </div>
        <div class="info_links">
          <ul>
            <li class=" ">
              <a class="" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="">
              <a class="" href="../../about.html"> About</a>
            </li>
            <li class="">
              <a class="" href="../../service.html"> Services </a>
            </li>
            <li class="">
              <a class="" href="../../news.html"> News</a>
            </li>
            <li class="">
              <a class="" href="../../contact.html">Contact Us</a>
            </li>
            <!-- <li class="">
              <a class="" href="#">Login</a>
            </li> -->
          </ul>
        </div>
        <div class="../../info_social">
          <div>
            <a href="">
              <img src="../../images/fb.png" alt="">
            </a>
          </div>
          <div>
            <a href="">
              <img src="../../images/twitter.png" alt="">
            </a>
          </div>
          <div>
            <a href="">
              <img src="../../images/linkedin.png" alt="">
            </a>
          </div>
          <div>
            <a href="">
              <img src="../../images/instagram.png" alt="">
            </a>
          </div>
        </div>
      </div>
    </div>

  </section>
  <section class="container-fluid footer_section">
    <div class="container">
      <p>
        &copy; 2021 All Rights Reserved By
        <a href="../../https://html.design/">Free Html Templates</a>
      </p>
    </div>
  </section>
  <!-- footer section -->

  <script type="text/javascript" src="../../js/jquery-3.4.1.min.js"></script>
  <script type="text/javascript" src="../../js/bootstrap.js"></script>
  <script type="text/javascript" src="../../https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
  </script>


  <!-- owl carousel script -->
  <script type="text/javascript">
    $(".owl-carousel").owlCarousel({
      loop: true,
      margin: 20,
      navText: [],
      autoplay: true,
      autoplayHoverPause: true,
      responsive: {
        0: {
          items: 1
        },
        768: {
          items: 2
        },
        1000: {
          items: 2
        }
      }
    });
  </script>
  <!-- end owl carousel script -->

</body>

</html>









 

