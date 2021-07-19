const express = require('express')
const app = express()
const mongoClient = require('mongodb').MongoClient

const url = "mongodb://localhost:27017" // mongodb default port

app.use(express.json())

mongoClient.connect(url, (err, db) => {
    if(err){
        console.log("Error while connecting mongo client")
    }
    else{
        const myDb = db.db('myDb')
        const collection = myDb.collection('myTable')

        app.post('/signup', (req, res) => {
            const newUser = {
                id: req.body.id,
                email: req.body.email,
                password: req.body.password
            }
            const query = {email: newUser.email}
            collection.findOne(query, (err, result) => {
                if(result === null){ // not registered
                    collection.insertOne(newUser, (err, result) => {
                        res.status(200).send()
                    })
                }
                else{ // already registered
                    res.status(400).send()
                }
            })
        })

        app.post('/login', (req, res) => {
            const query = {
                id: req.body.id,
                password: req.body.password
            }
            
            collection.findOne(query, (err, result) => {
                if(result !== null){
                    const objToSend = {
                        id: result.id,
                        email: result.email
                    }
                    res.status(200).send(JSON.stringify(objToSend))
                }
                else{ // wrong info
                    res.status(404).send() // obj not found
                }
            })
        })
    }
})

app.listen(3000, () => {
    console.log("Listenig on port 3000...")
})