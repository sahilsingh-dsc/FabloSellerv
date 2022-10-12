package com.myfablo.seller.orders.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllOrderResponseDataDetails {

    @SerializedName("job_seller_count")
    @Expose
    private Integer jobSellerCount;
    @SerializedName("job_status")
    @Expose
    private Integer jobStatus;
    @SerializedName("pd_or_appointment")
    @Expose
    private Integer pdOrAppointment;
    @SerializedName("business_type")
    @Expose
    private Integer businessType;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("job_id")
    @Expose
    private Integer jobId;
    @SerializedName("pickup_job_status")
    @Expose
    private Integer pickupJobStatus;
    @SerializedName("delivery_job_status")
    @Expose
    private Integer deliveryJobStatus;
    @SerializedName("seller_id")
    @Expose
    private Integer sellerId;
    @SerializedName("creation_datetime")
    @Expose
    private String creationDatetime;
    @SerializedName("job_delivery_datetime")
    @Expose
    private String jobDeliveryDatetime;
    @SerializedName("job_delivery_datetime_utc")
    @Expose
    private String jobDeliveryDatetimeUtc;
    @SerializedName("job_description")
    @Expose
    private String jobDescription;
    @SerializedName("marketplace_user_id")
    @Expose
    private Integer marketplaceUserId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("job_pickup_datetime")
    @Expose
    private String jobPickupDatetime;
    @SerializedName("job_address")
    @Expose
    private String jobAddress;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("customer_rating")
    @Expose
    private Object customerRating;
    @SerializedName("is_custom_order")
    @Expose
    private Integer isCustomOrder;
    @SerializedName("order_currency_symbol")
    @Expose
    private String orderCurrencySymbol;
    @SerializedName("total_amount")
    @Expose
    private Integer totalAmount;
    @SerializedName("order_amount")
    @Expose
    private Integer orderAmount;
    @SerializedName("delivery_method")
    @Expose
    private Integer deliveryMethod;
    @SerializedName("job_pickup_address")
    @Expose
    private String jobPickupAddress;
    @SerializedName("job_pickup_phone")
    @Expose
    private String jobPickupPhone;
    @SerializedName("job_pickup_name")
    @Expose
    private String jobPickupName;
    @SerializedName("pickup_tracking_link")
    @Expose
    private String pickupTrackingLink;
    @SerializedName("delivery_tracking_link")
    @Expose
    private String deliveryTrackingLink;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("customer_username")
    @Expose
    private String customerUsername;
    @SerializedName("task_type")
    @Expose
    private Integer taskType;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("transaction_status")
    @Expose
    private Object transactionStatus;
    @SerializedName("overall_transaction_status")
    @Expose
    private Object overallTransactionStatus;
    @SerializedName("vendor_is_deleted")
    @Expose
    private Integer vendorIsDeleted;
    @SerializedName("order_preparation_time")
    @Expose
    private Integer orderPreparationTime;
    @SerializedName("additionalPaymentViaLink")
    @Expose
    private Object additionalPaymentViaLink;
    @SerializedName("is_scheduled")
    @Expose
    private Integer isScheduled;
    @SerializedName("is_menu_enabled")
    @Expose
    private Integer isMenuEnabled;
    @SerializedName("edit_task")
    @Expose
    private Object editTask;
    @SerializedName("debt_amount")
    @Expose
    private Integer debtAmount;
    @SerializedName("product_details")
    @Expose
    private List<String> productDetails = null;
    @SerializedName("product_names")
    @Expose
    private String productNames;
    @SerializedName("product_ids")
    @Expose
    private String productIds;
    @SerializedName("delivery_system_job_id")
    @Expose
    private Object deliverySystemJobId;
    @SerializedName("merchant_name")
    @Expose
    private String merchantName;
    @SerializedName("store_name_json")
    @Expose
    private String storeNameJson;
    @SerializedName("storefront_user_id")
    @Expose
    private Integer storefrontUserId;
    @SerializedName("merchant_is_deleted")
    @Expose
    private Integer merchantIsDeleted;
    @SerializedName("job_pickup_datetime_utc")
    @Expose
    private String jobPickupDatetimeUtc;
    @SerializedName("job_delivery_new_datetime")
    @Expose
    private String jobDeliveryNewDatetime;
    @SerializedName("job_pickup_new_datetime")
    @Expose
    private String jobPickupNewDatetime;
    @SerializedName("show_refund_popup")
    @Expose
    private Integer showRefundPopup;
    @SerializedName("payment_method")
    @Expose
    private Integer paymentMethod;
    @SerializedName("can_change_status")
    @Expose
    private Integer canChangeStatus;
    @SerializedName("customer_is_deleted")
    @Expose
    private Integer customerIsDeleted;

    /**
     * No args constructor for use in serialization
     */
    public AllOrderResponseDataDetails() {
    }

    /**
     * @param pdOrAppointment
     * @param orderId
     * @param jobDeliveryNewDatetime
     * @param isScheduled
     * @param jobDeliveryDatetime
     * @param productDetails
     * @param merchantName
     * @param sellerId
     * @param jobPickupPhone
     * @param productNames
     * @param merchantIsDeleted
     * @param jobDeliveryDatetimeUtc
     * @param jobPickupDatetime
     * @param debtAmount
     * @param overallTransactionStatus
     * @param productId
     * @param pickupTrackingLink
     * @param vendorIsDeleted
     * @param transactionId
     * @param jobId
     * @param totalAmount
     * @param jobSellerCount
     * @param productIds
     * @param additionalPaymentViaLink
     * @param jobPickupDatetimeUtc
     * @param paymentMethod
     * @param jobDescription
     * @param pickupJobStatus
     * @param isMenuEnabled
     * @param deliveryTrackingLink
     * @param canChangeStatus
     * @param jobStatus
     * @param deliveryJobStatus
     * @param deliveryMethod
     * @param customerIsDeleted
     * @param isCustomOrder
     * @param paymentType
     * @param marketplaceUserId
     * @param taskType
     * @param editTask
     * @param orderAmount
     * @param storeNameJson
     * @param customerId
     * @param jobPickupName
     * @param customerUsername
     * @param transactionStatus
     * @param jobPickupAddress
     * @param jobPickupNewDatetime
     * @param userId
     * @param orderCurrencySymbol
     * @param creationDatetime
     * @param jobAddress
     * @param customerRating
     * @param showRefundPopup
     * @param orderPreparationTime
     * @param deliverySystemJobId
     * @param businessType
     * @param storefrontUserId
     */
    public AllOrderResponseDataDetails(Integer jobSellerCount, Integer jobStatus, Integer pdOrAppointment, Integer businessType, Integer userId, Integer orderId, Integer jobId, Integer pickupJobStatus, Integer deliveryJobStatus, Integer sellerId, String creationDatetime, String jobDeliveryDatetime, String jobDeliveryDatetimeUtc, String jobDescription, Integer marketplaceUserId, Integer productId, String jobPickupDatetime, String jobAddress, String paymentType, Object customerRating, Integer isCustomOrder, String orderCurrencySymbol, Integer totalAmount, Integer orderAmount, Integer deliveryMethod, String jobPickupAddress, String jobPickupPhone, String jobPickupName, String pickupTrackingLink, String deliveryTrackingLink, Integer customerId, String customerUsername, Integer taskType, String transactionId, Object transactionStatus, Object overallTransactionStatus, Integer vendorIsDeleted, Integer orderPreparationTime, Object additionalPaymentViaLink, Integer isScheduled, Integer isMenuEnabled, Object editTask, Integer debtAmount, List<String> productDetails, String productNames, String productIds, Object deliverySystemJobId, String merchantName, String storeNameJson, Integer storefrontUserId, Integer merchantIsDeleted, String jobPickupDatetimeUtc, String jobDeliveryNewDatetime, String jobPickupNewDatetime, Integer showRefundPopup, Integer paymentMethod, Integer canChangeStatus, Integer customerIsDeleted) {
        super();
        this.jobSellerCount = jobSellerCount;
        this.jobStatus = jobStatus;
        this.pdOrAppointment = pdOrAppointment;
        this.businessType = businessType;
        this.userId = userId;
        this.orderId = orderId;
        this.jobId = jobId;
        this.pickupJobStatus = pickupJobStatus;
        this.deliveryJobStatus = deliveryJobStatus;
        this.sellerId = sellerId;
        this.creationDatetime = creationDatetime;
        this.jobDeliveryDatetime = jobDeliveryDatetime;
        this.jobDeliveryDatetimeUtc = jobDeliveryDatetimeUtc;
        this.jobDescription = jobDescription;
        this.marketplaceUserId = marketplaceUserId;
        this.productId = productId;
        this.jobPickupDatetime = jobPickupDatetime;
        this.jobAddress = jobAddress;
        this.paymentType = paymentType;
        this.customerRating = customerRating;
        this.isCustomOrder = isCustomOrder;
        this.orderCurrencySymbol = orderCurrencySymbol;
        this.totalAmount = totalAmount;
        this.orderAmount = orderAmount;
        this.deliveryMethod = deliveryMethod;
        this.jobPickupAddress = jobPickupAddress;
        this.jobPickupPhone = jobPickupPhone;
        this.jobPickupName = jobPickupName;
        this.pickupTrackingLink = pickupTrackingLink;
        this.deliveryTrackingLink = deliveryTrackingLink;
        this.customerId = customerId;
        this.customerUsername = customerUsername;
        this.taskType = taskType;
        this.transactionId = transactionId;
        this.transactionStatus = transactionStatus;
        this.overallTransactionStatus = overallTransactionStatus;
        this.vendorIsDeleted = vendorIsDeleted;
        this.orderPreparationTime = orderPreparationTime;
        this.additionalPaymentViaLink = additionalPaymentViaLink;
        this.isScheduled = isScheduled;
        this.isMenuEnabled = isMenuEnabled;
        this.editTask = editTask;
        this.debtAmount = debtAmount;
        this.productDetails = productDetails;
        this.productNames = productNames;
        this.productIds = productIds;
        this.deliverySystemJobId = deliverySystemJobId;
        this.merchantName = merchantName;
        this.storeNameJson = storeNameJson;
        this.storefrontUserId = storefrontUserId;
        this.merchantIsDeleted = merchantIsDeleted;
        this.jobPickupDatetimeUtc = jobPickupDatetimeUtc;
        this.jobDeliveryNewDatetime = jobDeliveryNewDatetime;
        this.jobPickupNewDatetime = jobPickupNewDatetime;
        this.showRefundPopup = showRefundPopup;
        this.paymentMethod = paymentMethod;
        this.canChangeStatus = canChangeStatus;
        this.customerIsDeleted = customerIsDeleted;
    }

    public Integer getJobSellerCount() {
        return jobSellerCount;
    }

    public void setJobSellerCount(Integer jobSellerCount) {
        this.jobSellerCount = jobSellerCount;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getPdOrAppointment() {
        return pdOrAppointment;
    }

    public void setPdOrAppointment(Integer pdOrAppointment) {
        this.pdOrAppointment = pdOrAppointment;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getPickupJobStatus() {
        return pickupJobStatus;
    }

    public void setPickupJobStatus(Integer pickupJobStatus) {
        this.pickupJobStatus = pickupJobStatus;
    }

    public Integer getDeliveryJobStatus() {
        return deliveryJobStatus;
    }

    public void setDeliveryJobStatus(Integer deliveryJobStatus) {
        this.deliveryJobStatus = deliveryJobStatus;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(String creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    public String getJobDeliveryDatetime() {
        return jobDeliveryDatetime;
    }

    public void setJobDeliveryDatetime(String jobDeliveryDatetime) {
        this.jobDeliveryDatetime = jobDeliveryDatetime;
    }

    public String getJobDeliveryDatetimeUtc() {
        return jobDeliveryDatetimeUtc;
    }

    public void setJobDeliveryDatetimeUtc(String jobDeliveryDatetimeUtc) {
        this.jobDeliveryDatetimeUtc = jobDeliveryDatetimeUtc;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Integer getMarketplaceUserId() {
        return marketplaceUserId;
    }

    public void setMarketplaceUserId(Integer marketplaceUserId) {
        this.marketplaceUserId = marketplaceUserId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getJobPickupDatetime() {
        return jobPickupDatetime;
    }

    public void setJobPickupDatetime(String jobPickupDatetime) {
        this.jobPickupDatetime = jobPickupDatetime;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Object getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Object customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getIsCustomOrder() {
        return isCustomOrder;
    }

    public void setIsCustomOrder(Integer isCustomOrder) {
        this.isCustomOrder = isCustomOrder;
    }

    public String getOrderCurrencySymbol() {
        return orderCurrencySymbol;
    }

    public void setOrderCurrencySymbol(String orderCurrencySymbol) {
        this.orderCurrencySymbol = orderCurrencySymbol;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(Integer deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getJobPickupAddress() {
        return jobPickupAddress;
    }

    public void setJobPickupAddress(String jobPickupAddress) {
        this.jobPickupAddress = jobPickupAddress;
    }

    public String getJobPickupPhone() {
        return jobPickupPhone;
    }

    public void setJobPickupPhone(String jobPickupPhone) {
        this.jobPickupPhone = jobPickupPhone;
    }

    public String getJobPickupName() {
        return jobPickupName;
    }

    public void setJobPickupName(String jobPickupName) {
        this.jobPickupName = jobPickupName;
    }

    public String getPickupTrackingLink() {
        return pickupTrackingLink;
    }

    public void setPickupTrackingLink(String pickupTrackingLink) {
        this.pickupTrackingLink = pickupTrackingLink;
    }

    public String getDeliveryTrackingLink() {
        return deliveryTrackingLink;
    }

    public void setDeliveryTrackingLink(String deliveryTrackingLink) {
        this.deliveryTrackingLink = deliveryTrackingLink;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Object getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Object transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Object getOverallTransactionStatus() {
        return overallTransactionStatus;
    }

    public void setOverallTransactionStatus(Object overallTransactionStatus) {
        this.overallTransactionStatus = overallTransactionStatus;
    }

    public Integer getVendorIsDeleted() {
        return vendorIsDeleted;
    }

    public void setVendorIsDeleted(Integer vendorIsDeleted) {
        this.vendorIsDeleted = vendorIsDeleted;
    }

    public Integer getOrderPreparationTime() {
        return orderPreparationTime;
    }

    public void setOrderPreparationTime(Integer orderPreparationTime) {
        this.orderPreparationTime = orderPreparationTime;
    }

    public Object getAdditionalPaymentViaLink() {
        return additionalPaymentViaLink;
    }

    public void setAdditionalPaymentViaLink(Object additionalPaymentViaLink) {
        this.additionalPaymentViaLink = additionalPaymentViaLink;
    }

    public Integer getIsScheduled() {
        return isScheduled;
    }

    public void setIsScheduled(Integer isScheduled) {
        this.isScheduled = isScheduled;
    }

    public Integer getIsMenuEnabled() {
        return isMenuEnabled;
    }

    public void setIsMenuEnabled(Integer isMenuEnabled) {
        this.isMenuEnabled = isMenuEnabled;
    }

    public Object getEditTask() {
        return editTask;
    }

    public void setEditTask(Object editTask) {
        this.editTask = editTask;
    }

    public Integer getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(Integer debtAmount) {
        this.debtAmount = debtAmount;
    }

    public List<String> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<String> productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public Object getDeliverySystemJobId() {
        return deliverySystemJobId;
    }

    public void setDeliverySystemJobId(Object deliverySystemJobId) {
        this.deliverySystemJobId = deliverySystemJobId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getStoreNameJson() {
        return storeNameJson;
    }

    public void setStoreNameJson(String storeNameJson) {
        this.storeNameJson = storeNameJson;
    }

    public Integer getStorefrontUserId() {
        return storefrontUserId;
    }

    public void setStorefrontUserId(Integer storefrontUserId) {
        this.storefrontUserId = storefrontUserId;
    }

    public Integer getMerchantIsDeleted() {
        return merchantIsDeleted;
    }

    public void setMerchantIsDeleted(Integer merchantIsDeleted) {
        this.merchantIsDeleted = merchantIsDeleted;
    }

    public String getJobPickupDatetimeUtc() {
        return jobPickupDatetimeUtc;
    }

    public void setJobPickupDatetimeUtc(String jobPickupDatetimeUtc) {
        this.jobPickupDatetimeUtc = jobPickupDatetimeUtc;
    }

    public String getJobDeliveryNewDatetime() {
        return jobDeliveryNewDatetime;
    }

    public void setJobDeliveryNewDatetime(String jobDeliveryNewDatetime) {
        this.jobDeliveryNewDatetime = jobDeliveryNewDatetime;
    }

    public String getJobPickupNewDatetime() {
        return jobPickupNewDatetime;
    }

    public void setJobPickupNewDatetime(String jobPickupNewDatetime) {
        this.jobPickupNewDatetime = jobPickupNewDatetime;
    }

    public Integer getShowRefundPopup() {
        return showRefundPopup;
    }

    public void setShowRefundPopup(Integer showRefundPopup) {
        this.showRefundPopup = showRefundPopup;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getCanChangeStatus() {
        return canChangeStatus;
    }

    public void setCanChangeStatus(Integer canChangeStatus) {
        this.canChangeStatus = canChangeStatus;
    }

    public Integer getCustomerIsDeleted() {
        return customerIsDeleted;
    }

    public void setCustomerIsDeleted(Integer customerIsDeleted) {
        this.customerIsDeleted = customerIsDeleted;
    }

}