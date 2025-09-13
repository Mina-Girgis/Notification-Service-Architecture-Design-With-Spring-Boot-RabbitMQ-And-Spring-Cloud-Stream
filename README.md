# Notification Service

## 📌 Project Description
This project demonstrates how to build a **scalable notification service** using **Spring Boot**, **Spring Cloud Stream**, and **RabbitMQ**.  

The service is designed to handle different workloads and scenarios by providing **three architecture patterns**. Each type is tailored for specific use cases such as **low volume**, **high throughput**, or **priority-based** messaging.  

---

## 🗂️ Index of Architectures
1. [Type-1: Central Exchange with Routing Keys](#type-1-central-exchange-with-routing-keys)  
2. [Type-2: High,normal and low channels](#type-2-dedicated-queues-per-producer)  
3. [Type-3: Priority Queues with Dead Letter Handling](#type-3-priority-queues-with-dead-letter-handling)  

---

## 🔹 Type-1: Channel per type with priority
![Type-1 Architecture](./assets/type-1.png)

In this setup:
- All messages are published to a **single central exchange** (`notification.exchange`).  
- Each message includes a **routingKey** header (`email`, `sms`, `whatsapp`).  
- RabbitMQ uses these routing keys to deliver messages into the correct queue.  
- Consumers are bound to specific queues and process messages independently.  

## ✨ Features  
- Supports different types of notifications (**Email, SMS, WhatsApp**)  
- Configurable **retry policies** for each consumer  
- **Dead Letter Queues (DLQ)** for failed messages  
- **Priority queues** to process urgent messages first 


## ✅ Pros
- Simple to implement and maintain.  
- Easy to extend (just add a new queue + routing key for a new channel).  
- Centralized control → one exchange for all traffic.  
- Decouples producers from consumers (producers don’t need to know queue names).  

## ❌ Cons
- Low-priority messages will still be delivered, but they can experience significant delays if high-priority traffic continues to dominate the queue. 
- Single exchange could become a **bottleneck** under very high traffic.  
- Harder to apply **per-channel scaling rules** (all messages pass through the same exchange).  
  

---
## 🏗️ Type-2: Three Priority Channels (High, Normal, Low)
![Type-2 Architecture](./assets/type-2.png)
In this design, we maintain **three separate queues and consumers**:

- `notification.high` → handled by `highConsumer` (higher concurrency).
- `notification.normal` → handled by `normalConsumer`.
- `notification.low` → handled by `lowConsumer` (lower concurrency).

Each consumer can process **multiple notification types** (Email, SMS, WhatsApp), but the channel determines **priority**.

---


## ✨ Features
- Supports **different notification types**: Email, SMS, WhatsApp.
- **Priority-based processing** (High, Normal, Low).
- **Custom concurrency & prefetch** tuning per channel.
- **Dead Letter Queues (DLQ)** for failed messages.
- **Retry mechanism** with requeue/reprocess strategy.
- **Scalable design** → each channel can be scaled independently.


## ✅ Pros
- **Strict priority isolation** → high-priority messages are never blocked by low-priority traffic.
- **Custom concurrency per channel** → e.g., 5 workers for high, 3 for normal, 1 for low.
- **Independent scaling** → scale consumers per channel depending on workload.
- **Per-priority DLQ handling** → easier debugging and retries.
- **Traffic resilience** → a spike in low-priority traffic won’t impact high-priority processing.
- **At-least-once delivery** → thanks to consumer groups and RabbitMQ’s reliability.

---

## ❌ Cons
- **Infrastructure overhead** → more queues, DLQs, and bindings to manage.
- **Producer responsibility** → the sender must correctly choose the priority channel.
- **Possible resource waste** → high-priority workers may sit idle while low-priority lags.
- **No dynamic reprioritization** → once in `low`, a message can’t jump to `high` without re-publishing.
- **Complex retry logic** → each priority has its own DLQ; retries need coordination.
- **Risk of starvation** → low-priority traffic may be heavily delayed if high-priority is constant.

---


## 🔹 Type-3: Priority Queues with Dead Letter Handling
![Type-3 Architecture](./assets/type-3.png)

In this setup:
- Messages are sent to the central exchange with **priority** headers.  
- Each queue (`email`, `sms`, `whatsapp`) is configured with:  
  - `x-max-priority` → ensures urgent messages are processed first.  
  - `x-message-ttl` → controls message lifetime.  
  - `DLQ (Dead Letter Queue)` → failed messages are rerouted for later inspection.  
- Consumers use **retry policies with exponential backoff**.  

✅ **Best for:** **High throughput** and **mission-critical** notifications where priority and reliability matter.  

---

## 🚀 Example Request
```json
POST /notifications/notify
{
  "recipient": "john@example.com",
  "subject": "System Alert",
  "message": "Your account needs verification",
  "channel": "email",
  "priority": 9
}
```




## 📝 Note  
This project is **not focused on implementation details**.  
The main purpose is to demonstrate the **architectural approaches** and how to get the most benefit from **queues in RabbitMQ** when building a notification service.
