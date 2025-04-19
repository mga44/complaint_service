
# Starting application
Run
``
    docker compose up -d
``
to start MongoDb instance.

# Sample requests:

1. Create complaint
```
POST localhost:8080/complaint
   x-forwarded-for:103.112.60.255
   
   {
        "productId": 20,
        "complaintContent": "some message",
        "creationDate": "1970-04-11",
        "userId": 40
    }
```
2. Get all complaints
```
GET localhost:8080/complaint
```
3. Get one complaint
```
GET localhost:8080/complaint/{complaint_id}
```
4. Update complaint
```
PUT localhost:8080/{complaint_id}
{
    "complaintContent": "Sum complaint"
}
```
5. Delete complaint
```
DELETE localhost:8080/{complaint_id}
```