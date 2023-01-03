package com.myapp.hostel_management_system.service;

import com.myapp.hostel_management_system.entity.Hostel;
import com.myapp.hostel_management_system.entity.Student;
import com.myapp.hostel_management_system.repository.HostelRepository;
import org.apache.catalina.Host;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HostelService {
    private HostelRepository hostelRepository;

    public HostelService(HostelRepository hostelRepository) {
        this.hostelRepository = hostelRepository;
    }

    public List<Hostel> getAllHostel(){
        return hostelRepository.findAll();
    }
    public Hostel hostelSave(Hostel hostel){
        return hostelRepository.save(hostel);
    }
    public Hostel getHostelById(String id){
        return hostelRepository.findById(id).get();
    }
    public Hostel updateHostel(Hostel hostel){
        return hostelRepository.save(hostel);
    }
    public void deleteHostelById(String id){
        hostelRepository.deleteById(id);
    }
}
