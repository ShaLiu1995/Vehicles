import java.util.HashMap;


/******************************************************************************
 *  This program performs CRUD operations for a vehicle entity.
 * 
 *  @author Sha Liu
 *  Last updated: Oct. 31, 2017
 ******************************************************************************/

// Definition of the Vehicle class
class Vehicle {
    private int Id;
        private int Year;
        private String Make;
        private String Model;
        
        public Vehicle(int Id, int Year, String Make, String Model) {
        	this.Id = Id;
            this.Year = Year;
            this.Make = Make;
            this.Model = Model;
        }
        
        public int getVehicleId() {
        	return this.Id;
        }
        
        public int getVehicleYear() {
        	return this.Year;
        }
        
        public String getVehicleMake() {
        	return this.Make;
        }
        
        public String getVehicleModel() {
        	return this.Model;
        }
        
        public void setVehicleId(int Id) {
        	this.Id = Id;
        }
        
        public void setVehicleYear(int Year) {
        	this.Year = Year;
        }
        
        public void setVehicleMake(String Make) {
        	this.Make = Make;
        }
        
        public void setVehicleModel(String Model) {
        	this.Model = Model;
        }
        
        public void printVehicle() {
        	System.out.printf("Id: %d\tYear: %d\tMake: %s\tModel: %s\n", this.Id, this.Year, this.Make, this.Model);
        }
}

public class Solution{
    /**
     * This solution provides CRUD operations for Vehicle objects.
     * Create() method checks whether the object is legal before creating it.
     * Insert(), Get(), Delete() and Update() are overloaded to accept the varities of inputs.
     * PrintVehicleMap() can present all the entries in the the hash map which store the vehicles.
     */

    // Definite the code list for the properties used in Get()
    public final static int YEAR = 0;
    public final static int MAKE = 1;
    public final static int MODEL = 2;
    // The data structure for the in-memory storage of the vehicle objects
    public HashMap<Integer,Vehicle> vehicleMap = new HashMap<Integer,Vehicle>();

    public Vehicle Create(int id, int year, String make, String model) {
        if (make == null || make.length() == 0) {
        	System.out.println("Cannot create the entry. Make cannot be void.");
        	return null;
        }
        if (model == null || model.length() == 0) {
        	System.out.println("Cannot create the entry. Model cannot be void.");
        	return null;
        }
        if (year < 1950 || year > 2050) {
        	System.out.println("Cannot create the entry. Year " + year + " is illegal.");
            return null;
        }
        return new Vehicle(id, year, make, model);
    }
    
    // Insert a vehicle into the hash map
    public void Insert(Vehicle v) {
    	vehicleMap.put(v.getVehicleId(), v);
    }
    
    // Overload the Insert function
    public void Insert(int id, int year, String make, String model) {
    	Vehicle v = Create(id, year, make, model);
    	vehicleMap.put(v.getVehicleId(), v);
    }
 
    // Get the Id of the given vehicle
    public int Get(Vehicle v) {
        if (v == null) {
        	System.out.println("The vehicle does not exist.");
        	return -1;
        } 
        return v.getVehicleId();
    }
    
    // Get the vehicle with the given Id
    public Vehicle Get(int Id) {
    	if (!vehicleMap.containsKey(Id)) {
    		System.out.println("The vehicle with Id " + Id + " does not exist.");
    		return null;
    	}
    	return vehicleMap.get(Id);
    }

    
    // Return a hashmap contains all the entries with the given property
    public HashMap<Integer, Vehicle> Get(int property, String str) {
        HashMap<Integer, Vehicle> subMap = new HashMap<>();
        if (property == YEAR) {
            for (HashMap.Entry<Integer, Vehicle> entry : vehicleMap.entrySet()) {
                Vehicle v = entry.getValue();
                String year = Integer.toString(v.getVehicleYear());
                if (year.equals(str)) {
                    v.printVehicle();
                    subMap.put(v.getVehicleId(), v);
                }
            }
        }

        if (property == MAKE) {
            for (HashMap.Entry<Integer, Vehicle> entry : vehicleMap.entrySet()) {
                Vehicle v = entry.getValue();
                if (v.getVehicleMake().equals(str)) {
                    v.printVehicle();
                    subMap.put(v.getVehicleId(), v);
                }
            }
        }
        if (property == MODEL) {
            for (HashMap.Entry<Integer, Vehicle> entry : vehicleMap.entrySet()) {
                Vehicle v = entry.getValue();
                if (v.getVehicleModel().equals(str)) {
                    v.printVehicle();
                    subMap.put(v.getVehicleId(), v);
                }
            }
        }
        return subMap;
    }

    // Delete the vehicle with the given Id
    public Vehicle Delete(int Id) {
        if (!vehicleMap.containsKey(Id)) {
        	System.out.println("Vehicle with Id " + Id + " does not exist.");
            return null;
        }
        return vehicleMap.remove(Id);
    }
    
    // Overload the Delete function
    public Vehicle Delete(Vehicle v) {
        if (!vehicleMap.containsValue(v)) {
        	System.out.println("Vehicle does not exist.");
            return null;
        }
        return vehicleMap.remove(v.getVehicleId());
    }

    // Update a vehicle
    public void Update(Vehicle v) {
        if (!vehicleMap.containsKey(v.getVehicleId())) {
            System.out.println("Vehicle with Id " + v.getVehicleId() + " does not exist.");
        } else {
            vehicleMap.put(v.getVehicleId(), v);
            System.out.println("Vehicle with Id " + v.getVehicleId() + " is updated.");
        }
    }
    
    // Overload the Update function to accept all arguments
    public void Update(int id, int year, String make, String model) {
        Vehicle v = Create(id, year, make, model);
        // Check whether it is legal to update. If illegel, Create will return null
        if (v == null) return;

        if (!vehicleMap.containsKey(id)) {
            System.out.println("Vehicle with Id " + id + " does not exist.");
        } else {
            vehicleMap.put(id, v);
            System.out.println("Vehicle with Id " + v.getVehicleId() + " is updated.");
        }
    }

    // Update only Year
    public void UpdateYear(int id, int year){
        Vehicle v  = Get(id);
        if (v == null) {
            System.out.println("Vehicle with Id " + id + " does not exist.");
        } else {
            Update(id, year, v.getVehicleMake(), v.getVehicleModel());
        }
    }

    // Update only Make
    public void UpdateMake(int id, String make){
        Vehicle v  = Get(id);
        if (v == null) {
            System.out.println("Vehicle with Id " + id + " does not exist.");
        } else {
            Update(id, v.getVehicleYear(), make, v.getVehicleModel());
        }
    }

    // Update only Model
    public void UpdateModel(int id, String model){
        Vehicle v  = Get(id);
        if (v == null) {
            System.out.println("Vehicle with Id " + id + " does not exist.");
        } else {
            Update(id, v.getVehicleYear(), v.getVehicleMake(), model);
        }
    }

    // Print out all entries in the hash map
    public void printVehicleMap() {
    	for (HashMap.Entry<Integer, Vehicle> entry : vehicleMap.entrySet()) {
    		entry.getValue().printVehicle();
    	}
    }
    
    //Automated Test
    public static void main(String args[]) {
		Solution solution = new Solution();
    	Vehicle v1 = new Vehicle(123456, 2017, "Ford", "Raptor");
        Vehicle v2 = new Vehicle(348726, 1980, "VW", "Golf");
    	Vehicle v3 = new Vehicle(228556, 2015, "Ford", "Focus");
        Vehicle v4 = new Vehicle(828473, 2009, "Audi", "A6");

    	solution.Insert(v1);
    	solution.Insert(v2);
        solution.Insert(v3);
        solution.Insert(v4);
        System.out.println("Now we have four entries:");
    	solution.printVehicleMap();
        System.out.println();

        System.out.println("Get the vehicle with id " + v3.getVehicleId());
        solution.Get(v3.getVehicleId()).printVehicle();
        System.out.println();

        System.out.println("Get all the vehicles made by " + "Ford");
        solution.Get(MAKE, "Ford");
        System.out.println();

        System.out.println("Delete the vehicle with id " + v3.getVehicleId());
        solution.Delete(v3);
        solution.printVehicleMap();
        System.out.println();

        Vehicle v_to_update = new Vehicle(348726, 1981, "VW", "Beetle");
        System.out.println("Upate the vehicle with id " + v2.getVehicleId() + " to ");
        v_to_update.printVehicle();
        solution.Update(v_to_update);
        solution.printVehicleMap();
        System.out.println();

        System.out.println("Upate Year of the vehicle with id " + v2.getVehicleId() + " to " + 1981);
        solution.UpdateYear(v2.getVehicleId(), 1981);
        solution.printVehicleMap();
        System.out.println();

        System.out.println("Upate Make of the vehicle with id " + v2.getVehicleId() + " to " + "Volvo");
        solution.UpdateMake(v2.getVehicleId(), "Volvo");
        solution.printVehicleMap();
        System.out.println();

        System.out.println("Upate Model of the vehicle with id " + v2.getVehicleId() + " to " + "s740");
        solution.UpdateModel(v2.getVehicleId(), "740");
        solution.printVehicleMap();
        System.out.println();
        // Test illegal update
        System.out.println("Upate Year of the vehicle with id " + v2.getVehicleId() + " to an illegal value of " + 1949);
        solution.UpdateYear(v2.getVehicleId(), 1949);
        System.out.println();
        
        System.out.println("Upate Year of the vehicle with id " + v2.getVehicleId() + " to an illegal value of " + 2051);
        solution.UpdateYear(v2.getVehicleId(), 2051);
        System.out.println();

        System.out.println("Upate Make of the vehicle with id " + v2.getVehicleId() + " to a void string ");
        solution.UpdateMake(v2.getVehicleId(), "");
        System.out.println();

        System.out.println("Upate Model of the vehicle with id " + v2.getVehicleId() + " to a void string ");
        solution.UpdateModel(v2.getVehicleId(), "");
        System.out.println();
        
        

    }
      
}
